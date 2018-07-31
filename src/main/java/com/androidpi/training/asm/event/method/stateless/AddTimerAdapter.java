package com.androidpi.training.asm.event.method.stateless;


import com.androidpi.training.asm.SampleClass;
import com.androidpi.training.asm.event.clazz.generating.AsmClassLoader;
import com.androidpi.training.asm.event.clazz.parsing.ClassPrinterHelper;
import org.objectweb.asm.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.objectweb.asm.Opcodes.*;

/**
 * Add a timer to calculate total time spend in each class.
 */
public class AddTimerAdapter extends ClassVisitor {

    private String owner;
    private boolean isInterface;

    public AddTimerAdapter(ClassVisitor cv) {
        super(ASM6, cv);
    }

    @Override
    public void visit(int version, int access, String name,
                      String signature, String superName, String[] interfaces) {
        cv.visit(version, access, name, signature, superName, interfaces);
        owner = name;
        isInterface = (access & ACC_INTERFACE) != 0;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name,
                                     String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature,
                exceptions);
        if (!isInterface && mv != null && !name.equals("<init>")) {
            mv = new AddTimerMethodAdapter(mv, owner);
        }
        return mv;
    }

    @Override
    public void visitEnd() {
        if (!isInterface) {
            FieldVisitor fv = cv.visitField(ACC_PUBLIC + ACC_STATIC, "timer",
                    "J", null, null);
            if (fv != null) {
                fv.visitEnd();
            }
        }
        cv.visitEnd();
    }

    public static void main(String[] args) throws Exception{
        ClassReader cr = new ClassReader(SampleClass.class.getName());
        ClassWriter cw = new ClassWriter(0);
        cr.accept(new AddTimerAdapter(cw), 0);

        byte[] transformedClass = cw.toByteArray();
        // print tranformed class
        ClassPrinterHelper.printClass(transformedClass);

        // print timer after execute some methods on transformed class's instance.
        AsmClassLoader classLoader = new AsmClassLoader();
        Class aClass = classLoader.loadClass(transformedClass);

        Object obj = aClass.newInstance();
        Method doThis = aClass.getDeclaredMethod("doThis");
        doThis.invoke(obj);
        doThis.invoke(obj);
        doThis.invoke(obj);

        // Time spent should be around 3 seconds.

        Field timerField = aClass.getField("timer");
        long timeSpend = timerField.getLong(aClass);

        System.out.printf("Time spent to execute methods of transformed SampleClass: %dms\n", timeSpend);
    }
}

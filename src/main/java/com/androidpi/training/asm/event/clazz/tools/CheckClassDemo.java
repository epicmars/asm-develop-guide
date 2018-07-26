package com.androidpi.training.asm.event.clazz.tools;


import com.androidpi.training.asm.event.clazz.generating.AsmClassLoader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.util.CheckClassAdapter;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.PrintWriter;
import java.lang.reflect.Method;

import static org.objectweb.asm.Opcodes.*;

public class CheckClassDemo {

    /**
     * Generate an interface.
     * package pkg;
     * public interface Comparable extends Mesurable {
     * int LESS = -1;
     * int EQUAL = 0;
     * int GREATER = 1;
     * int compareTo(Object o);
     * }
     */
    public static void generateInterface() {
        ClassWriter cw = new ClassWriter(0);
        TraceClassVisitor tcv = new TraceClassVisitor(cw, new PrintWriter(System.out));
        CheckClassAdapter cv = new CheckClassAdapter(tcv);
        cv.visit(V1_8, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE,
                "pkg/Comparable", null, "java/lang/Object",
                new String[]{"pkg/Mesurable"});
        cv.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "I",
                null, new Integer(-1)).visitEnd();
        cv.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I",
                null, new Integer(0)).visitEnd();
        cv.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I",
                null, new Integer(1)).visitEnd();
        // A Method
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "compareTo",
                "(Ljava/lang/Object;)I", null, null);
        mv.visitEnd();
        cv.visitEnd();
    }

    /**
     * Generate a class;
     * package pkg;
     * public class HelloWorld {
     * <p>
     * public void hello() {
     * System.out.println("Hello world!");
     * }
     * }
     */
    public static void generateClass() throws Exception {
        ClassWriter cw = new ClassWriter(0);
        TraceClassVisitor tcv = new TraceClassVisitor(cw, new PrintWriter(System.out));
        CheckClassAdapter cv = new CheckClassAdapter(tcv);
        cv.visit(V1_8, ACC_PUBLIC,
                "pkg/HelloWorld", null, "java/lang/Object",
                null);
        // Generate constructor.
        MethodVisitor constructorMV = cv.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        constructorMV.visitCode();
        constructorMV.visitVarInsn(ALOAD, 0);
        constructorMV.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        constructorMV.visitInsn(RETURN);
        constructorMV.visitMaxs(2,1);
        constructorMV.visitEnd();
        // Generate a method that print "Hello world!" to standard output.
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "hello",
                "()V", null, null);
        mv.visitCode();
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("Hello world!");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        mv.visitInsn(RETURN);
        mv.visitMaxs(2, 1);
        mv.visitEnd();
        cv.visitEnd();
        byte[] b = cw.toByteArray();

        AsmClassLoader classLoader = new AsmClassLoader();
        Class helloWorldClass = classLoader.loadClass(b);
        Object helloWorld = helloWorldClass.newInstance();
        Method method = helloWorldClass.getDeclaredMethod("hello");
        method.invoke(helloWorld);
    }

    public static void main(String[] args) throws Exception {

        // Generate Comparable interface.
        generateInterface();

        // Generate HelloWorld class and load dynamically.
        generateClass();
    }
}

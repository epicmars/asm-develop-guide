package com.androidpi.training.asm.event.clazz.transforming;


import com.androidpi.training.asm.SampleClass;
import com.androidpi.training.asm.event.clazz.parsing.ClassPrinter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ASM6;
import static org.objectweb.asm.Opcodes.V1_8;

public class ClassVersionAdapter extends ClassVisitor {

    public ClassVersionAdapter(int api) {
        super(api);
    }

    public ClassVersionAdapter(ClassVisitor classVisitor) {
        super(ASM6, classVisitor);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        if (cv != null) {
            cv.visit(V1_8, access, name, signature, superName, interfaces);
        } else {
            super.visit(version, access, name, signature, superName, interfaces);
        }
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

    public static void main(String[] args) throws Exception{
        ClassReader cr = new ClassReader(SampleClass.class.getName());
        ClassWriter cw = new ClassWriter(0);
        cr.accept(new ClassVersionAdapter(new ClassPrinter(cw)), 0);
    }
}

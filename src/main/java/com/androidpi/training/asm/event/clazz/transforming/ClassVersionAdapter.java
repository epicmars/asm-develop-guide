package com.androidpi.training.asm.event.clazz.transforming;


import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.V1_8;

public class ClassVersionAdapter extends ClassVisitor {

    public ClassVersionAdapter(int api) {
        super(api);
    }

    public ClassVersionAdapter(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
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
}

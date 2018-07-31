package com.androidpi.training.asm.event.method.transforming;


import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ASM6;

public class RemoveNopClassAdapter extends ClassVisitor {

    public RemoveNopClassAdapter(ClassVisitor cv) {
        super(ASM6, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        if (mv != null) {
            // construct a method adapter chain
            mv = new RemoveNopAdapter(mv);
        }
        return mv;
    }
}

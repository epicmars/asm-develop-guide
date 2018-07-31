package com.androidpi.training.asm.event.method.transforming;


import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ASM6;
import static org.objectweb.asm.Opcodes.NOP;

/**
 * Remove NOP instructions inside methods.
 */
public class RemoveNopAdapter extends MethodVisitor {

    public RemoveNopAdapter(MethodVisitor mv) {
        super(ASM6, mv);
    }

    @Override
    public void visitInsn(int opcode) {
        if (NOP != opcode) {
            super.visitInsn(opcode);
        }
    }
}

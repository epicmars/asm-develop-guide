package com.androidpi.training.asm.event.method.statefull;

import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class RemoveAddZeroAdapter extends PatternMethodAdapter {

    private static int SEEN_ICONST_0 = 1;

    public RemoveAddZeroAdapter(MethodVisitor mv) {
        super(ASM6, mv);
    }

    @Override
    public void visitInsn(int opcode) {
        if (state == SEEN_ICONST_0) {
            if (opcode == IADD) {
                state = SEEN_NOTHING;
                return;
            }
        }
        visitInsn();
        if (opcode == ICONST_0) {
            state = SEEN_ICONST_0;
            return;
        }
        mv.visitInsn(opcode);
    }

    @Override
    protected void visitInsn() {
        if (state == SEEN_ICONST_0) {
            mv.visitInsn(ICONST_0);
        }
        state = SEEN_NOTHING;
    }
}

package com.androidpi.training.asm.event.method.tools;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AnalyzerAdapter;

import static org.objectweb.asm.Opcodes.*;

/**
 * Compute frames with AnalyzerAdapter.
 */
class AddTimerMethodAdapter3 extends AnalyzerAdapter {
    private String owner;

    public AddTimerMethodAdapter3(String owner, int access,
                                  String name, String desc, MethodVisitor mv) {
        super(ASM6, owner, access, name, desc, mv);
        this.owner = owner;
    }

    @Override
    public void visitCode() {
        super.visitCode();
        super.visitFieldInsn(GETSTATIC, owner, "timer", "J");
        super.visitMethodInsn(INVOKESTATIC, "java/lang/System",
                "currentTimeMillis", "()J", false);
        super.visitInsn(LSUB);
        super.visitFieldInsn(PUTSTATIC, owner, "timer", "J");
    }

    @Override
    public void visitInsn(int opcode) {
        if ((opcode >= IRETURN && opcode <= RETURN) || opcode == ATHROW) {
            super.visitFieldInsn(GETSTATIC, owner, "timer", "J");
            super.visitMethodInsn(INVOKESTATIC, "java/lang/System",
                    "currentTimeMillis", "()J", false);
            super.visitInsn(LADD);
            super.visitFieldInsn(PUTSTATIC, owner, "timer", "J");
        }
        super.visitInsn(opcode);
    }
}
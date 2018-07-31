package com.androidpi.training.asm.event.method.tools;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AnalyzerAdapter;

import static org.objectweb.asm.Opcodes.*;

class AddTimerMethodAdapter2 extends AnalyzerAdapter {
    private int maxStack;
    private String owner;

    public AddTimerMethodAdapter2(String owner, int access,
                                  String name, String desc, MethodVisitor mv) {
        super(ASM6, owner, access, name, desc, mv);
        this.owner = owner;
    }

    @Override
    public void visitCode() {
        super.visitCode();
        mv.visitFieldInsn(GETSTATIC, owner, "timer", "J");
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System",
                "currentTimeMillis", "()J", false);
        mv.visitInsn(LSUB);
        mv.visitFieldInsn(PUTSTATIC, owner, "timer", "J");
        maxStack = 4;
    }

    @Override
    public void visitInsn(int opcode) {
        if ((opcode >= IRETURN && opcode <= RETURN) || opcode == ATHROW) {
            mv.visitFieldInsn(GETSTATIC, owner, "timer", "J");
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System",
                    "currentTimeMillis", "()J", false);
            mv.visitInsn(LADD);
            mv.visitFieldInsn(PUTSTATIC, owner, "timer", "J");
            maxStack = Math.max(maxStack, stack.size() + 4);
        }
        super.visitInsn(opcode);
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        super.visitMaxs(Math.max(this.maxStack, maxStack), maxLocals);
    }
}
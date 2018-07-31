package com.androidpi.training.asm.event.method.tools;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

class AddTimerMethodAdapter6 extends AdviceAdapter {
    private String owner;
    public AddTimerMethodAdapter6(String owner, int access, String name, String desc,
                                  MethodVisitor mv) {
        super(ASM6, mv, access, name, desc);
        this.owner = owner;
    }

    @Override
    protected void onMethodEnter() {
        mv.visitFieldInsn(GETSTATIC, owner, "timer", "J");
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System",
                "currentTimeMillis", "()J", false);
        mv.visitInsn(LSUB);
        mv.visitFieldInsn(PUTSTATIC, owner, "timer", "J");
    }

    @Override
    protected void onMethodExit(int opcode) {
        mv.visitFieldInsn(GETSTATIC, owner, "timer", "J");
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System",
                "currentTimeMillis", "()J", false);
        mv.visitInsn(LADD);
        mv.visitFieldInsn(PUTSTATIC, owner, "timer", "J");
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        super.visitMaxs(maxStack + 4, maxLocals);
    }
}
package com.androidpi.training.asm.event.method.stateless;

import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

/**
 * Transform class's each method below:
 *
 * public class C {
 *     public void m() throws Exception {
 *         doSth();
 *     }
 *     ...
 * }
 *
 * Into this:
 *
 * public class C {
 *     public static long timer;
 *     public void m() throws Exception {
 *          timer -= System.currentTimeMillis();
 *          doSth();
 *          timer += System.currentTimeMillis();
 *     }
 *     ...
 * }
 *
 * So we can calculate the time spent on all method invocations
 * of all instances of this class.
 */
public class AddTimerMethodAdapter extends MethodVisitor {

    private String owner;

    public AddTimerMethodAdapter(MethodVisitor mv, String owner) {
        super(ASM6, mv);
        this.owner = owner;
    }

    @Override
    public void visitCode() {
        mv.visitCode();
        mv.visitFieldInsn(GETSTATIC, owner, "timer", "J"); mv.visitMethodInsn(INVOKESTATIC, "java/lang/System",
                "currentTimeMillis", "()J", false);
        mv.visitInsn(LSUB);
        mv.visitFieldInsn(PUTSTATIC, owner, "timer", "J");
    }

    @Override
    public void visitInsn(int opcode) {
        if ((opcode >= IRETURN && opcode <= RETURN) || opcode == ATHROW) {
            mv.visitFieldInsn(GETSTATIC, owner, "timer", "J");
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System",
                    "currentTimeMillis", "()J", false);
            mv.visitInsn(LADD);
            mv.visitFieldInsn(PUTSTATIC, owner, "timer", "J");
        }
        mv.visitInsn(opcode);
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        mv.visitMaxs(maxStack + 4, maxLocals);
    }
}

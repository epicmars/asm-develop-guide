package com.androidpi.training.asm.event.method.tools;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.LocalVariablesSorter;

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
 *          long t = System.currentTimeMillis();
 *          doSth();
 *          timer += System.currentTimeMillis() - t;
 *     }
 *     ...
 * }
 *
 * So we can calculate the time spent on all method invocations
 * of all instances of this class.
 */
public class AddTimerMethodAdapter4 extends LocalVariablesSorter {

    private String owner;
    private int time;

    public AddTimerMethodAdapter4(String owner, int access, String desc,
                                  MethodVisitor mv) {
        super(ASM6, access, desc, mv);
        this.owner = owner;
    }

    @Override
    public void visitCode() {
        super.visitCode();
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System",
                "currentTimeMillis", "()J", false);
        time = newLocal(Type.LONG_TYPE);
        mv.visitVarInsn(LSTORE, time);
    }

    @Override
    public void visitInsn(int opcode) {
        if ((opcode >= IRETURN && opcode <= RETURN) || opcode == ATHROW) {
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System",
                    "currentTimeMillis", "()J", false);
            mv.visitVarInsn(LLOAD, time);
            mv.visitInsn(LSUB);
            mv.visitFieldInsn(GETSTATIC, owner, "timer", "J");
            mv.visitInsn(LADD);
            mv.visitFieldInsn(PUTSTATIC, owner, "timer", "J");
        }
        super.visitInsn(opcode);
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        mv.visitMaxs(maxStack + 4, maxLocals);
    }
}
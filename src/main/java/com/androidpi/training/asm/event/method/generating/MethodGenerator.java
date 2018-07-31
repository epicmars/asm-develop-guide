package com.androidpi.training.asm.event.method.generating;

import com.androidpi.training.asm.event.clazz.parsing.ClassPrinterHelper;
import org.objectweb.asm.*;

import static org.objectweb.asm.Opcodes.*;

/**
 * Generate class below with ASMifier:
 *
 * package com.androidpi.training.asm;
 * public class Bean {
 *     private int f;
 *
 *     public int getF() {
 *         return this.f;
 *     }
 *
 *     public void setF(int f) {
 *         this.f = f;
 *     }
 *
 *     public void checkAndSetF(int f) {
 *         if (f >= 0) {
 *           this.f = f;
 *         } else {
 *           throw new IllegalArgumentException();
 *         }
 *     }
 * }
 *
 * It's byte code presentation:
 *
 * // class version 52.0 (52)
 * // access flags 0x21
 * public class com/androidpi/training/asm/Bean {
 *
 *   // compiled from: Bean.java
 *
 *   // access flags 0x2
 *   private I f
 *
 *   // access flags 0x1
 *   public <init>()V
 *    L0
 *     LINENUMBER 2 L0
 *     ALOAD 0
 *     INVOKESPECIAL java/lang/Object.<init> ()V
 *     RETURN
 *    L1
 *     LOCALVARIABLE this Lcom/androidpi/training/asm/Bean; L0 L1 0
 *     MAXSTACK = 1
 *     MAXLOCALS = 1
 *
 *   // access flags 0x1
 *   public getF()I
 *    L0
 *     LINENUMBER 6 L0
 *     ALOAD 0
 *     GETFIELD com/androidpi/training/asm/Bean.f : I
 *     IRETURN
 *    L1
 *     LOCALVARIABLE this Lcom/androidpi/training/asm/Bean; L0 L1 0
 *     MAXSTACK = 1
 *     MAXLOCALS = 1
 *
 *   // access flags 0x1
 *   public setF(I)V
 *    L0
 *     LINENUMBER 10 L0
 *     ALOAD 0
 *     ILOAD 1
 *     PUTFIELD com/androidpi/training/asm/Bean.f : I
 *    L1
 *     LINENUMBER 11 L1
 *     RETURN
 *    L2
 *     LOCALVARIABLE this Lcom/androidpi/training/asm/Bean; L0 L2 0
 *     LOCALVARIABLE f I L0 L2 1
 *     MAXSTACK = 2
 *     MAXLOCALS = 2
 *
 *   // access flags 0x1
 *   public checkAndSetF(I)V
 *    L0
 *     LINENUMBER 14 L0
 *     ILOAD 1
 *     IFLT L1
 *    L2
 *     LINENUMBER 15 L2
 *     ALOAD 0
 *     ILOAD 1
 *     PUTFIELD com/androidpi/training/asm/Bean.f : I
 *     GOTO L3
 *    L1
 *     LINENUMBER 17 L1
 *    FRAME SAME
 *     NEW java/lang/IllegalArgumentException
 *     DUP
 *     INVOKESPECIAL java/lang/IllegalArgumentException.<init> ()V
 *     ATHROW
 *    L3
 *     LINENUMBER 19 L3
 *    FRAME SAME
 *     RETURN
 *    L4
 *     LOCALVARIABLE this Lcom/androidpi/training/asm/Bean; L0 L4 0
 *     LOCALVARIABLE f I L0 L4 1
 *     MAXSTACK = 2
 *     MAXLOCALS = 2
 * }
 *
 */
public class MethodGenerator {

    // Note the code below is generated with Asmifier.
    public static void main(String[] args) throws Exception{

        ClassWriter classWriter = new ClassWriter(0);
        FieldVisitor fieldVisitor;
        MethodVisitor methodVisitor;
        AnnotationVisitor annotationVisitor0;

        classWriter.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "com/androidpi/training/asm/Bean", null, "java/lang/Object", null);

        classWriter.visitSource("Bean.java", null);

        {
            fieldVisitor = classWriter.visitField(ACC_PRIVATE, "f", "I", null, null);
            fieldVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(2, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            methodVisitor.visitInsn(RETURN);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLocalVariable("this", "Lcom/androidpi/training/asm/Bean;", null, label0, label1, 0);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "getF", "()I", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(6, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitFieldInsn(GETFIELD, "com/androidpi/training/asm/Bean", "f", "I");
            methodVisitor.visitInsn(IRETURN);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLocalVariable("this", "Lcom/androidpi/training/asm/Bean;", null, label0, label1, 0);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "setF", "(I)V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(10, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitVarInsn(ILOAD, 1);
            methodVisitor.visitFieldInsn(PUTFIELD, "com/androidpi/training/asm/Bean", "f", "I");
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(11, label1);
            methodVisitor.visitInsn(RETURN);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLocalVariable("this", "Lcom/androidpi/training/asm/Bean;", null, label0, label2, 0);
            methodVisitor.visitLocalVariable("f", "I", null, label0, label2, 1);
            methodVisitor.visitMaxs(2, 2);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "checkAndSetF", "(I)V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(14, label0);
            methodVisitor.visitVarInsn(ILOAD, 1);
            Label label1 = new Label();
            methodVisitor.visitJumpInsn(IFLT, label1);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLineNumber(15, label2);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitVarInsn(ILOAD, 1);
            methodVisitor.visitFieldInsn(PUTFIELD, "com/androidpi/training/asm/Bean", "f", "I");
            Label label3 = new Label();
            methodVisitor.visitJumpInsn(GOTO, label3);
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(17, label1);
            methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            methodVisitor.visitTypeInsn(NEW, "java/lang/IllegalArgumentException");
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/IllegalArgumentException", "<init>", "()V", false);
            methodVisitor.visitInsn(ATHROW);
            methodVisitor.visitLabel(label3);
            methodVisitor.visitLineNumber(19, label3);
            methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            methodVisitor.visitInsn(RETURN);
            Label label4 = new Label();
            methodVisitor.visitLabel(label4);
            methodVisitor.visitLocalVariable("this", "Lcom/androidpi/training/asm/Bean;", null, label0, label4, 0);
            methodVisitor.visitLocalVariable("f", "I", null, label0, label4, 1);
            methodVisitor.visitMaxs(2, 2);
            methodVisitor.visitEnd();
        }
        classWriter.visitEnd();

        ClassPrinterHelper.printClass(classWriter.toByteArray());
    }
}

package com.androidpi.training.asm.event.clazz.tools;

import com.androidpi.training.asm.SampleClass;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.PrintWriter;

public class TraceClassDemo {

    /**
     * // class version 52.0 (52)
     * // access flags 0x21
     * public class com/androidpi/training/asm/SampleClass extends com/androidpi/training/asm/SampleAbstractClass  implements com/androidpi/training/asm/SampleInterface  {
     *
     *   // compiled from: SampleClass.java
     *   // access flags 0x9
     *   public static INNERCLASS com/androidpi/training/asm/SampleClass$SampleInnerClass com/androidpi/training/asm/SampleClass SampleInnerClass
     *
     *   // access flags 0x2
     *   private I x
     *
     *   // access flags 0x2
     *   private I y
     *
     *   // access flags 0x1
     *   public Ljava/lang/String; name
     *
     *   // access flags 0x1
     *   public <init>()V
     *    L0
     *     LINENUMBER 3 L0
     *     ALOAD 0
     *     INVOKESPECIAL com/androidpi/training/asm/SampleAbstractClass.<init> ()V
     *     RETURN
     *    L1
     *     LOCALVARIABLE this Lcom/androidpi/training/asm/SampleClass; L0 L1 0
     *     MAXSTACK = 1
     *     MAXLOCALS = 1
     *
     *   // access flags 0x1
     *   public doSomething(Ljava/lang/String;)V
     *    L0
     *     LINENUMBER 12 L0
     *     RETURN
     *    L1
     *     LOCALVARIABLE this Lcom/androidpi/training/asm/SampleClass; L0 L1 0
     *     LOCALVARIABLE input Ljava/lang/String; L0 L1 1
     *     MAXSTACK = 0
     *     MAXLOCALS = 2
     *
     *   // access flags 0x1
     *   public doThis()V
     *    L0
     *     LINENUMBER 17 L0
     *     RETURN
     *    L1
     *     LOCALVARIABLE this Lcom/androidpi/training/asm/SampleClass; L0 L1 0
     *     MAXSTACK = 0
     *     MAXLOCALS = 1
     *
     *   // access flags 0x1
     *   public doThat()I
     *    L0
     *     LINENUMBER 21 L0
     *     ICONST_0
     *     IRETURN
     *    L1
     *     LOCALVARIABLE this Lcom/androidpi/training/asm/SampleClass; L0 L1 0
     *     MAXSTACK = 1
     *     MAXLOCALS = 1
     *
     *   // access flags 0x1
     *   public aMethodOfSampleClass(Ljava/lang/String;)Ljava/lang/String;
     *    L0
     *     LINENUMBER 25 L0
     *     LDC "return : %s"
     *     ICONST_1
     *     ANEWARRAY java/lang/Object
     *     DUP
     *     ICONST_0
     *     ALOAD 1
     *     AASTORE
     *     INVOKESTATIC java/lang/String.format (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
     *     ARETURN
     *    L1
     *     LOCALVARIABLE this Lcom/androidpi/training/asm/SampleClass; L0 L1 0
     *     LOCALVARIABLE arg Ljava/lang/String; L0 L1 1
     *     MAXSTACK = 5
     *     MAXLOCALS = 2
     * }
     *
     * Process finished with exit code 0
     */
    public static void main(String[] args) throws Exception{
        ClassReader cr = new ClassReader(SampleClass.class.getName());
        cr.accept(new TraceClassVisitor(new PrintWriter(System.out)), 0);
    }
}

package com.androidpi.training.asm.event.clazz.members;

import com.androidpi.training.asm.SampleClass;
import com.androidpi.training.asm.event.clazz.parsing.ClassPrinterHelper;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import static org.objectweb.asm.Opcodes.ASM6;

/**
 * If you don't forward one of the visit method，the corresponding class member
 * is removed.
 *
 * This class adapter removes the information about outer and inner classes,
 * as well as the name of the source file. The resulting class remains fully
 * functional, because these elements are only used for debugging purposes.
 */
public class RemoveDebugAdapter extends ClassVisitor {

    public RemoveDebugAdapter(int api) {
        super(api);
    }

    public RemoveDebugAdapter(ClassVisitor classVisitor) {
        super(ASM6, classVisitor);
    }

    @Override
    public void visitOuterClass(String owner, String name, String descriptor) {
        // pass
    }

    @Override
    public void visitInnerClass(String name, String outerName, String innerName, int access) {
        // pass
    }

    public static void main(String[] args) throws Exception{

        // Remove inner class information of SampleClass
        ClassReader outerClassReader = new ClassReader(SampleClass.class.getName());
        ClassWriter outerCw = new ClassWriter(0);
        RemoveDebugAdapter removeDebugOuterAdapter = new RemoveDebugAdapter(outerCw);
        outerClassReader.accept(removeDebugOuterAdapter, 0);

        // Print SampleClass After removing inner class information.
        ClassPrinterHelper.printClass(outerCw.toByteArray());

        // Remove outer class information of SampleClass.SampleInnerClass
        ClassReader innerClassReader = new ClassReader(SampleClass.SampleInnerClass.class.getName());
        ClassWriter cwInner = new ClassWriter(0);
        RemoveDebugAdapter removeDebugInnerAdapter = new RemoveDebugAdapter(cwInner);
        innerClassReader.accept(removeDebugInnerAdapter, 0);

        // Print SampleInnerClass after removing outer class information.
        ClassPrinterHelper.printClass(cwInner.toByteArray());
    }
}

package com.androidpi.training.asm.event.clazz.parsing;


import com.androidpi.training.asm.SampleClass;
import org.objectweb.asm.*;

import java.io.IOException;
import java.util.Arrays;

import static org.objectweb.asm.Opcodes.ASM6;

/**
 * A ClassVisitor that print all of the information about a class.
 */
public class ClassPrinter extends ClassVisitor {

    public ClassPrinter() {
        super(ASM6);
    }

    public ClassPrinter(int api) {
        super(api);
    }

    public ClassPrinter(ClassVisitor classVisitor) {
        super(ASM6, classVisitor);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        System.out.println("\n//----------------------------------------------------");
        System.out.printf("// visit(%d %d %s %s %s %s)\n", version, access, name, signature, superName, Arrays.toString(interfaces));
        System.out.println("//----------------------------------------------------");
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public void visitSource(String source, String debug) {
        System.out.printf("// visitSource(%s %s)\n", source, debug);
        super.visitSource(source, debug);
    }

    @Override
    public ModuleVisitor visitModule(String name, int access, String version) {
        System.out.printf("// visitModule(%s %d %s)\n", name, access, version);
        return super.visitModule(name, access, version);
    }

    @Override
    public void visitOuterClass(String owner, String name, String descriptor) {
        System.out.printf("// visitOuterClass(%s %s %s)\n", owner, name, descriptor);
        super.visitOuterClass(owner, name, descriptor);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        System.out.printf("// visitAnnotation(%s %b)\n", descriptor, visible);
        return super.visitAnnotation(descriptor, visible);
    }

    @Override
    public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
        System.out.printf("// visitTypeAnnotation(%d %s %s %b)\n", typeRef, typePath.toString(), descriptor, visible);
        return super.visitTypeAnnotation(typeRef, typePath, descriptor, visible);
    }

    @Override
    public void visitAttribute(Attribute attribute) {
        System.out.printf("// visitAttribute(%s)\n", attribute.toString());
        super.visitAttribute(attribute);
    }

    @Override
    public void visitInnerClass(String name, String outerName, String innerName, int access) {
        System.out.printf("// visiInnerClass(%s %s %s %d)\n", name, outerName, innerName, access);
        super.visitInnerClass(name, outerName, innerName, access);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        System.out.printf("// visitField(%d %s %s %s %s)\n", access, name, descriptor, signature, value);
        return super.visitField(access, name, descriptor, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        System.out.printf("// visitMethod(%d %s %s %s %s)\n", access, name, descriptor, signature, Arrays.toString(exceptions));
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

    @Override
    public void visitEnd() {
        System.out.printf("// visitEnd\n");
        super.visitEnd();
    }

    public static void main(String[] args) throws IOException {
        ClassReader cr = new ClassReader(SampleClass.class.getCanonicalName());
        cr.accept(new ClassPrinter(ASM6), 0);
    }
}

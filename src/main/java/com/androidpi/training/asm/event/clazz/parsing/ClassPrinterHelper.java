package com.androidpi.training.asm.event.clazz.parsing;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.PrintWriter;

public class ClassPrinterHelper {

    public static void printClass(Class<?> clazz) throws Exception{
        ClassReader cr = new ClassReader(clazz.getName());
        cr.accept(new ClassPrinter(new TraceClassVisitor(new PrintWriter(System.out))), 0);
    }

    public static void printClass(byte[] byteArray) {
        ClassReader cr = new ClassReader(byteArray);
        cr.accept(new ClassPrinter(new TraceClassVisitor(new PrintWriter(System.out))), 0);
    }
}

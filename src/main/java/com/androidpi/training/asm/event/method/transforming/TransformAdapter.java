package com.androidpi.training.asm.event.method.transforming;


import org.objectweb.asm.ClassVisitor;

public class TransformAdapter extends ClassVisitor {

    public TransformAdapter(int api) {
        super(api);
    }

    public TransformAdapter(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }
}

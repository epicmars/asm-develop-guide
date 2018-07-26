package com.androidpi.training.asm.event.clazz.members;

import com.androidpi.training.asm.SampleClass;
import com.androidpi.training.asm.event.clazz.parsing.ClassPrinter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ASM6;

/**
 * Instead of forwarding fewer class than you receive, you can "forward"
 * more, which has the effect of adding class members.
 *
 * We must respect the predefined order of visitXxx method, the only possible
 * locations are visitInnerClass, visitField, visitMethod or visitEnd methods.
 *
 * If we put new visit call in visitField or visitMethod, several fields will be
 * added: one per field or method in the original class.
 *
 * In fact, the only truly correct solution is to add new members by making
 * additional calls in the visitEnd method.Because a class must not contain
 * duplicate members, and the only way to be sure that a new member is unique
 * is to compare it with all the existing members, which can only be done once
 * they have all been visited, i.e. in the visitEnd method.
 *
 * Note: The tree API does not have this limitations, it's possible to add new
 * members at any time inside a transformation.
 */
public class AddFieldAdapter extends ClassVisitor {

    private int fAcc;
    private String fName;
    private String fDesc;
    private boolean isFieldPresent;
    public AddFieldAdapter(ClassVisitor cv, int fAcc, String fName,
                           String fDesc) {
        super(ASM6, cv);
        this.fAcc = fAcc;
        this.fName = fName;
        this.fDesc = fDesc;
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc,
                                   String signature, Object value) {
        if (name.equals(fName)) {
            isFieldPresent = true;
        }
        return super.visitField(access, name, desc, signature, value);
    }

    @Override
    public void visitEnd() {
        if (!isFieldPresent) {
            FieldVisitor fv = super.visitField(fAcc, fName, fDesc, null, null);
            if (fv != null) {
                fv.visitEnd();
            }
        }
        super.visitEnd();
    }

    public static void main(String[] args) throws Exception{

        ClassReader cr = new ClassReader(SampleClass.class.getName());
        ClassWriter cw = new ClassWriter(0);
        AddFieldAdapter addFieldAdapter = new AddFieldAdapter(cw, ACC_PUBLIC, "sampleStringField", "Ljava/lang/String;");
        cr.accept(addFieldAdapter, 0);

        // After inserting new field.
        ClassReader afterCr = new ClassReader(cw.toByteArray());
        afterCr.accept(new ClassPrinter(), 0);
    }
}

package com.example.demo.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Generator {

    public static void main(String[] args) throws IOException {
        ClassReader classReader = new ClassReader("com/example/demo/asm/AsmDemo");

        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        ClassVisitor classVisitor = new MyClassVisitor(classWriter);
        classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);

        byte[] bytes = classWriter.toByteArray();

        Files.write(Paths.get("E:/AsmDemo.class"), bytes);
    }
}

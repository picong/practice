package com.example.demo.io;

import java.io.*;

public class ObjectStream {


    public static void main(String[] args) {
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;

        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream("E:/student.txt"));
            objectOutputStream.writeObject(new Student("gg", 22));
            objectOutputStream.writeObject(new Student("tt", 18));
            objectOutputStream.writeObject(new Student("rr", 17));

            objectInputStream = new ObjectInputStream(new FileInputStream("E:/student.txt"));
            for (int i = 0; i < 3; i++) {
                System.out.println(objectInputStream.readObject());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                objectInputStream.close();
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class Student implements Serializable {
        private String name;
        private int age;

        public Student(String name, int age) {
            super();
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student [name=" + name + ", age=" + age + "]";
        }

    }

}
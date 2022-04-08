package com.example.demo.io;

import java.io.*;
import java.util.Vector;

public class SequenceInputStreamTest {

    public static void main(String[] args) {
        doSequence();
    }

    private static void doSequence() {
        // 创建一个合并流的对象
        SequenceInputStream sis = null;
        // 创建输出流
        BufferedOutputStream bos = null;
        try {
            // 构建流集合
            Vector<InputStream> list = new Vector<>();
            list.addElement(new FileInputStream("E:/demo/1.txt"));
            list.addElement(new FileInputStream("E:/demo/2.txt"));
            list.addElement(new FileInputStream("E:/demo/3.txt"));

            sis = new SequenceInputStream(list.elements());

            bos = new BufferedOutputStream(new FileOutputStream("E:/demo/4.txt"));
            // 读写数据
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = sis.read(buf)) != -1) {
               bos.write(buf, 0, len);
               bos.flush();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (sis != null)
                    sis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bos != null)
                    bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

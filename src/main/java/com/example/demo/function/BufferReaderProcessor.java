package com.example.demo.function;

import java.io.BufferedReader;
import java.io.IOException;

@FunctionalInterface
public interface BufferReaderProcessor {

    /**
     *
     * @param br
     * @return
     * @throws IOException
     */
    String processor(BufferedReader br) throws IOException;

}

package io;

//: io/LargeMappedFiles.java
// Creating a very large file using mapping.
// {RunByHand}

import java.nio.*;
import java.nio.channels.*;
import java.io.*;

public class LargeMappedFiles {
    private static final int LENGTH = 0x8FFFFFF; // 128 MB
    // 1 073 741 824
    //

    public static void main(String[] args) throws Exception {
        MappedByteBuffer out = new RandomAccessFile("test.dat", "rw").getChannel()
                .map(FileChannel.MapMode.READ_WRITE, 0, LENGTH);

        for (int i = 0; i < LENGTH; i++) {
            out.put((byte) 'x');
        }

        print("Finished writing");

        for (int i = LENGTH / 2; i < LENGTH / 2 + 6; i++) {
            printnb((char) out.get(i));
        }
    }

    private static void printnb(char c) {
        System.out.print(c);
    }

    private static void print(String s) {
        System.out.println(s);
    }
} ///:~
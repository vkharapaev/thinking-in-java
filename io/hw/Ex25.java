package io.hw;

import java.nio.*;
import java.nio.channels.*;
import java.io.*;

public class Ex25 {
    private static final int NUM_OF_INTS = 1_000_000_000;
    private static final int NUM_OF_UBUFF_INTS = 200_000;

    private abstract static class Tester {
        private String name;

        public Tester(String name) {
            this.name = name;
        }

        public void runTest() {
            System.out.print(name + ": ");
            try {
                long start = System.nanoTime();
                test();
                double duration = System.nanoTime() - start;
                System.out.format("%.2f sec\n", duration / 1.0e9);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public abstract void test() throws IOException;
    }

    private static Tester[] tests = {

            new Tester("ByteBuffer.allocate") {
                public void test() throws IOException {
                    ByteBuffer bb = ByteBuffer.allocate(NUM_OF_INTS);
                    while (bb.hasRemaining()) {
                        bb.put((byte) 1);
                    }
                    bb.rewind();
                    while (bb.hasRemaining()) {
                        bb.get();
                    }
                }
            },

            new Tester("ByteBuffer.allocateDirect") {
                public void test() throws IOException {
                    ByteBuffer bb = ByteBuffer.allocateDirect(NUM_OF_INTS);
                    while (bb.hasRemaining()) {
                        bb.put((byte) 1);
                    }
                    bb.rewind();
                    while (bb.hasRemaining()) {
                        bb.get();
                    }
                }
            },

    };

    public static void main(String[] args) {
        for (Tester test : tests) {
            test.runTest();
        }
    }
}

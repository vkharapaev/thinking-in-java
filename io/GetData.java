package io;

import java.nio.ByteBuffer;

public class GetData {
    private static final int BSIZE = 1024;

    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(BSIZE);

        // Allocation automatically zeroes the ByteBuffer:
        int i = 0;
        while (i++ < bb.limit()) {
            if (bb.get() != 0) {
                print("nonzero");
            }
        }
        print("i = " + i);
        bb.rewind();

        // Store and read a char array:
        bb.asCharBuffer().put("Howdy!");
        char c;
        while ((c = bb.getChar()) != 0) {
            printnb(c + " ");
        }
        print();
        bb.rewind();

        // Store and read a short:
        bb.asShortBuffer().put((short) 1142);
        print(bb.getShort());
        bb.rewind();

        // Store and read an int:
        bb.asIntBuffer().put(99471142);
        print(bb.getInt());
        bb.rewind();

        // Store and read a long:
        bb.asLongBuffer().put(99471142);
        print(bb.getLong());
        bb.rewind();

        // Store and read a float:
        bb.asFloatBuffer().put(99471142);
        print(bb.getFloat());
        bb.rewind();

        // Store and read a double:
        bb.asDoubleBuffer().put(99471142);
        print(bb.getDouble());
        bb.rewind();
    }

    private static void print(double aDouble) {
        System.out.println("double " + aDouble);
    }

    private static void print(float aFloat) {
        System.out.println("float " + aFloat);
    }

    private static void print(long aLong) {
        System.out.println("long " + aLong);
    }

    private static void print(int integer) {
        System.out.println("integer " + integer);
    }

    private static void print(short num) {
        System.out.println("short " + num);
    }

    private static void printnb(String message) {
        System.out.print(message);
    }

    private static void print() {
        System.out.println();
    }

    private static void print(String message) {
        System.out.println(message);
    }
}
/* Output:
i = 1025
H o w d y !
12390
99471142
99471142
9.9471144E7
9.9471142E7
684 Thinking in Java Bruce Eckel
*///:~
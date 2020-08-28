package io;

//: io/ViewBuffers.java

import java.nio.*;

public class ViewBuffers {
    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[]{0, 0, 0, 0, 0, 0, 0, 'a'});
        bb.rewind();
        printnb("Byte Buffer ");
        while (bb.hasRemaining()) {
            printnb(bb.position() + " -> " + bb.get() + ", ");
        }
        print();
        CharBuffer cb = ((ByteBuffer) bb.rewind()).asCharBuffer();
        printnb("Char Buffer ");
        while (cb.hasRemaining()) {
            printnb(cb.position() + " -> " + cb.get() + ", ");
        }
        print();
        FloatBuffer fb = ((ByteBuffer) bb.rewind()).asFloatBuffer();
        printnb("Float Buffer ");
        while (fb.hasRemaining()) {
            printnb(fb.position() + " -> " + fb.get() + ", ");
        }
        print();
        IntBuffer ib = ((ByteBuffer) bb.rewind()).asIntBuffer();
        printnb("Int Buffer ");
        while (ib.hasRemaining()) {
            printnb(ib.position() + " -> " + ib.get() + ", ");
        }
        print();
        LongBuffer lb = ((ByteBuffer) bb.rewind()).asLongBuffer();
        printnb("Long Buffer ");
        while (lb.hasRemaining()) {
            printnb(lb.position() + " -> " + lb.get() + ", ");
        }
        print();
        ShortBuffer sb = ((ByteBuffer) bb.rewind()).asShortBuffer();
        printnb("Short Buffer ");
        while (sb.hasRemaining()) {
            printnb(sb.position() + " -> " + sb.get() + ", ");
        }
        print();
        DoubleBuffer db = ((ByteBuffer) bb.rewind()).asDoubleBuffer();
        printnb("Double Buffer ");
        while (db.hasRemaining()) {
            printnb(db.position() + " -> " + db.get() + ", ");
        }
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
} /* Output:
Byte Buffer 0 -> 0, 1 -> 0, 2 -> 0, 3 -> 0, 4 -> 0, 5 -> 0, 6 -> 0, 7 -> 97,
Char Buffer 0 -> , 1 -> , 2 -> , 3 -> a,
Float Buffer 0 -> 0.0, 1 -> 1.36E-43,
Int Buffer 0 -> 0, 1 -> 97,
Long Buffer 0 -> 97,
Short Buffer 0 -> 0, 1 -> 0, 2 -> 0, 3 -> 97,
Double Buffer 0 -> 4.8E-322,
*///:~
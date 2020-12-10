package io.hw;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.EnumSet;
import java.util.zip.DeflaterOutputStream;

public class Test {
    enum Days {MON, TWO, WEN, THUR, FRIDAY, SET, SUN}
    public static void main(String[] args) {
        EnumSet<Days> range = EnumSet.range(Days.THUR, Days.SET);
//        range.forEach(System.out::println);
//        EnumSet.allOf(Days.class).forEach(System.out::println);
        EnumSet.copyOf(range).forEach(System.out::println);
    }
}

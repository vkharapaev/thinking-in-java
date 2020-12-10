package io;

//: io/GZIPcompress.java
// {Args: GZIPcompress.java}

import java.util.zip.*;
import java.io.*;

public class GZIPCompress {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Usage: \nGZIPCompress file \n\tUses GZIP compression to compress the file to test.gz");
//            System.exit(1);
            args = new String[]{"src/io/FileOutputShortcut.out"};
        }

        System.out.println("Writing file");
        BufferedReader in = new BufferedReader(new FileReader(args[0]));
        BufferedOutputStream out = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream("test.gz")));
        int c;
        while ((c = in.read()) != -1) {
            out.write(c);
        }
        in.close();
        out.close();

        System.out.println("Reading file");
        BufferedReader in2 = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream("test.gz"))));
        String s;
        while ((s = in2.readLine()) != null) {
            System.out.println(s);
        }
    }
} /* (Execute to see output) *///:~
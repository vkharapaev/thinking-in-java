package io;

import java.io.*;

// Привет мир

public class BasicFileOutput {
    static String file = "src/io/BasicFileOutput.out";

    public static void main(String[] args) throws IOException {
        LineNumberReader in = new LineNumberReader(new StringReader(BufferedInputFile.read("src/io/BasicFileOutput.java")));

        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            String s;
            while ((s = in.readLine()) != null) {
                out.println(in.getLineNumber() + ": " + s);
            }
        }

// Show the stored file:
        System.out.println(BufferedInputFile.read(file));
    }
}
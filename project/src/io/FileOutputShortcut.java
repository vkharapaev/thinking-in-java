package io;

import java.io.*;

// Привет мир!

public class FileOutputShortcut {
    static String file = "src/io/FileOutputShortcut.out";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new StringReader(BufferedInputFile.read("src/io/FileOutputShortcut.java")));
// Here’s the shortcut:
        PrintWriter out = new PrintWriter(file);
        int lineCount = 1;
        String s;
        while ((s = in.readLine()) != null)
            out.println(lineCount++ + ": " + s);
        out.close();
// Show the stored file:
        System.out.println(BufferedInputFile.read(file));
    }
}
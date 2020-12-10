package io.hw;

import io.PPrint;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by WOW on 23.08.2020.
 */
public class Ex7 {
    public static void main(String[] args) throws IOException {



        LineNumberReader reader = new LineNumberReader(new FileReader("src/io/hw/Ex7.java"));
        String line;
        LinkedList<String> fileData = new LinkedList<>();
        while ((line = reader.readLine()) != null) {

            if (line.matches(String.format(".*%s.*", "main"))) {
                System.out.println(reader.getLineNumber());
            }

            fileData.add(line);
        }

        PPrint.pprint(fileData);
    }
}

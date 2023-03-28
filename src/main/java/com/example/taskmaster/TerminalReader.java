package com.example.taskmaster;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class TerminalReader {

    public static void TerminalToFile(String filePath) {
        try {
            File file = new File(filePath);
            FileOutputStream fos = new FileOutputStream(file);
            PrintStream ps = new PrintStream(fos);
            System.setOut(ps);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

package com.example.demo.java.systemout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class SystemOutTest {
    public static void main(String[] args) throws FileNotFoundException {
        System.setOut(new PrintStream(new FileOutputStream(new File("E:/Tmp/test.log"))));
        System.out.println("hello");
    }
}

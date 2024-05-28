package com.example.demo.java.classloader;

public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader classLoader = new MyClassLoader();
        Class c1 = classLoader.loadClass("User");
        Class c2 = classLoader.loadClass("User");

        System.out.println(c1 == c2);
    }
}

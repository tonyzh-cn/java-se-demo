package com.example.demo.java.classloader;

import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader{
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        InputStream is = getClass().getResourceAsStream(name+".class");
        String fullName = new ClassLoaderTest().getClass().getCanonicalName();
        name = fullName.substring(0,fullName.lastIndexOf("."))+"."+name;

        try {
            byte []b = new byte[is.available()];
            is.read(b);
            return defineClass(name,b,0,b.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name);
        }
    }
}

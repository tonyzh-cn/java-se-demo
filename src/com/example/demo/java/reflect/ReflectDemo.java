package com.example.demo.java.reflect;

import java.lang.reflect.Field;

public class ReflectDemo {

    public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        User user = new User();
        user.setIsStudent(true);
        Class clazz = new User().getClass();
        Field field = clazz.getDeclaredField("isStudent");
        field.setAccessible(true);
        System.out.println((boolean) field.get(user) ? 1 : 0);
    }

}

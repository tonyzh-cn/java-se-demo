package com.example.demo.java.list;

import java.util.Arrays;
import java.util.List;

public class AsListTest {
    public static void main(String[] args) {
        Integer[] a = {1, 2, 3};
        List l = Arrays.asList(a);
        System.out.println(l.size());
    }
}

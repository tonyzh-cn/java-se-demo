package com.example.demo.java.stream;

import java.util.Arrays;
import java.util.List;

public class ForEachDemo {
    public static void customPrint(String s) {
        System.out.println("hello " + s);
    }

    public static void main(String[] args) {
        List<String> strList = Arrays.asList("YangHang", "AnXiaoHei", "LiuPengFei");
        strList.forEach(ForEachDemo::customPrint);
    }

}

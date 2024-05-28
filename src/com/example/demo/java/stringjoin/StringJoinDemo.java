package com.example.demo.java.stringjoin;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class StringJoinDemo {
    public static void main(String[] args) {
        StringJoiner sj = new StringJoiner(",", "[", "]");
        sj.add("zhangsan").add("lisi");
        System.out.println(sj.toString());

        List<String> list = Arrays.asList("zhangsan", "lisi");
        System.out.println(list.stream().collect(Collectors.joining(",", "[", "]")));
    }
}

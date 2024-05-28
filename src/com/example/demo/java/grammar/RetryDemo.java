package com.example.demo.java.grammar;

public class RetryDemo {

    public static void main(String[] args) {
        retry:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println(j);
                if (j == 3) {
                    continue retry;
                }
            }
        }

    }

}

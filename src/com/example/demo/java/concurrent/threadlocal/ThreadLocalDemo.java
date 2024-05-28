package com.example.demo.java.concurrent.threadlocal;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalDemo {
    private static final int HASH_INCREMENT = 0x61c88647;
    private static AtomicInteger nextHashCode = new AtomicInteger();

    public static void main(String[] args) {
        for (int i = 0; i <= 16; i++) {
            if (i == 10) {
                System.out.println(i);
            }
            ThreadLocal<Integer> tl = new ThreadLocal<>();
            tl.set(1);
        }

        System.out.println(HASH_INCREMENT);
        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Integer.toBinaryString(HASH_INCREMENT));
//        System.out.println(1 << 31);

        for (int i = 1; i <= 48; i++) {
//            if(i%16 == 1){
//                System.out.println("###################################################");
//            }
//            System.out.println(nextHashCode.getAndAdd(HASH_INCREMENT) & ((1<<30)-1));
//            System.out.println(nextHashCode.getAndAdd(3));
            int v = nextHashCode.getAndAdd(HASH_INCREMENT);
            System.out.println(v);
            System.out.println(Integer.toBinaryString(v));
        }

    }
}

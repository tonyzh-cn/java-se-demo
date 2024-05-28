package com.example.demo.java.leetcode;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangtao
 * @since 2024/5/28 16:32
 */
public class TenBillionPhoneNumber {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(10*1000);
        Map<String, BitSet> map = new HashMap();

        for(int i=10;i<100;i++){
            BitSet bitSet = map.computeIfAbsent("1" + i, k -> new BitSet());
            for(int j=10;j<=99;j++){
                bitSet.set(j);
            }
        }

        BitSet bitSet = map.get("110");
        assert !bitSet.get(9);
//
        assert bitSet.get(10);

        Thread.sleep(Integer.MAX_VALUE);
    }

//    static class BitSet{
//        byte []data =new byte[100000000/8];
//
//        public void add(int n){
//            int index=(n-1)/8;
//            data[index] = (byte) (data[index] | (0x01 << (n%8)));
//        }
//
//        public boolean exists(int n){
//            int index=(n-1)/8;
//            int pos=n%8;
//
//            return (data[index] & 0x01<<pos) != 0;
//        }
//    }
}

package com.example.demo.java.leetcode;

/**
 * @author zhangtao
 * @since 2024/5/28 13:56
 */
public class TenBillionNumber {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(10*1000);
        byte []data=new byte[1000000000/8];

        for (int i = 1; i <= 1000000000; i++) {
            int index=(i-1)/8;
            data[index] = (byte) (data[index] | (0x01 << (i%8)));
        }

        assert exists(data,1);

        assert exists(data,1000000000);

        assert !exists(data,0);


        assert !exists(data,1000000001);


        Thread.sleep(Integer.MAX_VALUE);
    }

    private static boolean exists(byte []data,int n){
        int index=(n-1)/8;
        int pos=n%8;

        return (data[index] & 0x01<<pos) != 0;
    }
}

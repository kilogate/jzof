package com.kilogate.jzof.chapter02.bitoperation;

/**
 * 二进制中 1 的个数
 *
 * @author fengquanwei
 * @create 2020/3/9 下午9:12
 **/
public class CalculateCountOfOne {
    public static int calculateCountOfOne1(int n) {
        int count = 0;
        int bit = 1;

        while (bit != 0) {
            if ((bit & n) == bit) {
                count++;
            }

            bit = bit << 1;
        }

        return count;
    }

    /**
     * 把一个整数减去1，再和原整数做与运算，会把该数字最右边的1变成0
     * 那么一个整数的二进制表示中有多少个1，就可以进行多少次这样的操作
     */
    public static int calculateCountOfOne2(int n) {
        int count = 0;

        while (n != 0) {
            n = (n - 1) & n;
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 32; i++) {
            int num = (int) Math.pow(2, i) - 1;
            System.out.println(String.format("num: %s, binary: %s, countOf1: %s", num, Integer.toBinaryString(num), calculateCountOfOne2(num)));
        }

        System.out.println(String.format("num: %s, binary: %s, countOf1: %s", -1, Integer.toBinaryString(-1), calculateCountOfOne2(-1)));
    }
}

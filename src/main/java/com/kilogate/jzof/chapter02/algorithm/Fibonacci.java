package com.kilogate.jzof.chapter02.algorithm;

/**
 * 菲波那切数列
 *
 * @author fengquanwei
 * @create 2020/3/1 下午2:46
 **/
public class Fibonacci {
    /**
     * 基于递归的实现效率低，重复计算子问题，且有可能会导致调用栈溢出
     */
    public static int fibonacci1(int n) {
        if (n <= 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        return fibonacci1(n - 1) + fibonacci1(n - 2);
    }

    /**
     * 基于循环的实现
     */
    public static int fibonacci2(int n) {
        if (n <= 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        int result = 0;

        int minusOne = 1;
        int minusTwo = 0;

        for (int i = 2; i <= n; i++) {
            result = minusOne + minusTwo;
            minusTwo = minusOne;
            minusOne = result;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(fibonacci2(10));
    }
}

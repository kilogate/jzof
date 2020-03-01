package com.kilogate.jzof.chapter02.algorithm;

/**
 * 青蛙跳台阶问题
 * <p>
 * 一只青蛙一次可以跳上一级台阶，也可以跳上两级台阶。求该青蛙跳上一个n级的台阶总共有多少种跳发。
 *
 * @author fengquanwei
 * @create 2020/3/1 下午3:10
 **/
public class FrogJumpStep {
    /**
     * 基于递归的实现
     */
    public static int jumpStep1(int n) {
        if (n <= 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        return jumpStep1(n - 1) + jumpStep1(n - 2);
    }

    /**
     * 基于循环的实现
     */
    public static int jumpStep2(int n) {
        if (n <= 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        int result = 0;

        int beforeOne = 2;
        int beforeTwo = 1;

        for (int i = 3; i <= n; i++) {
            result = beforeOne + beforeTwo;
            beforeTwo = beforeOne;
            beforeOne = result;
        }

        return result;
    }

    /**
     * 每次可以跳 1~n 级台阶，跳上 n 级台阶的跳法
     */
    public static int jumpAnyStep(int n) {
        if (n <= 0) {
            return 0;
        }

        /**
         * 数学归纳法可证明：f(n) = 2^(n-1)
         *
         * f(1) = 1
         * f(2) = f(1) + f(0)
         *
         * f(3) = f(2) + f(1)
         * f(4) = f(3) + f(2) + f(1)
         * f(5) = f(4) + f(3) + f(2) + f(1)
         *
         * f(n) = f(n-1) + f(n-2) + ... + f(1) 式子一
         * f(n+1) = f(n) + f(n-1) + ... + f(1) 式子二
         *
         * 式子二 - 式子一
         *
         * f(n+1) - f(n) = f(n) 即 f(n+1) = 2 * f(n) 即 f(n) = 2 * f(n-1) 即 f(n) = 2^(n-1) * f(1) 即 f(n) = 2^(n-1)
         */

        return (int) Math.pow(2, n - 1);
    }

    public static void main(String[] args) {
        System.out.println(jumpStep2(9));
        System.out.println(jumpAnyStep(9));
    }
}

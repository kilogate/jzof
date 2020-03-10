package com.kilogate.jzof.chapter03;

/**
 * 幂运算（不需要考虑大数问题）
 *
 * @author fengquanwei
 * @create 2020/3/10 下午11:52
 **/
public class PowOperation {
    public static double pow(double base, int exponent) {
        // 0的零次幂非法，0的负次幂函非法
        if (base == 0) {
            return 0;
        }

        if (exponent == 0) {
            return 1;
        }

        // 是否需要求导？指数小于0时先求乘积再求导
        boolean derivation = exponent < 0;

        if (derivation) {
            exponent = Math.abs(exponent);
        }

        double result = doPow(base, exponent);

        if (derivation) {
            result = 1.0 / result;
        }

        return result;
    }

    private static double doPow(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }

        if (exponent == 1) {
            return base;
        }

        double subResult = doPow(base, exponent >> 1);

        double result = subResult * subResult;

        if ((exponent & 1) == 1) {
            result *= base;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(pow(0, -1));
        System.out.println(pow(0, 0));
        System.out.println(pow(0, -1));
        System.out.println(pow(-2.0, 2));
        System.out.println(pow(2, -1));
    }
}

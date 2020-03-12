package com.kilogate.jzof.chapter03;

/**
 * 打印从 1 到最大的 n 位数
 *
 * @author fengquanwei
 * @create 2020/3/12 下午10:51
 **/
public class PrintNumbersOfNDigit {
    public static void printNumbersOfNDigit(int n) {
        if (n <= 0) {
            return;
        }

        int[] num = new int[n];

        while (increaseNumber(num)) {
            printNumber(num);
        }
    }

    private static boolean increaseNumber(int[] num) {
        int index = num.length - 1;

        while (index >= 0) {
            if (num[index] < 9) {
                num[index]++;
                return true;
            }

            num[index] = 0;
            index--;
        }

        return false;
    }

    private static void printNumber(int[] num) {
        StringBuffer result = new StringBuffer();

        boolean begin = false;

        for (int i = 0; i < num.length; i++) {
            if (num[i] != 0) {
                begin = true;
            }

            if (begin) {
                result.append(num[i]);
            }
        }

        System.out.println(result.toString());
    }

    public static void main(String[] args) {
        // 【警告】n 不要设置太大，cpu 会飙升发烫
        printNumbersOfNDigit(5);
    }
}

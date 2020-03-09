package com.kilogate.jzof.chapter02.algorithm;

/**
 * 剪绳子
 * <p>
 * 给定长度为n的绳子，把绳子剪成m段，每段长度都是整数，使得所有段长度的乘积最大。
 * 求最大的乘积。
 *
 * @author fengquanwei
 * @create 2020/3/8 下午10:17
 **/
public class CutRope {
    /**
     * 求剪绳子方案的最大乘积（动态规划）
     * f(0) = 1
     * f(1) = 1
     * f(n) = max(i*f(n-i)) (0<i<=n)
     */
    public static int calculateMaxProductByDP(int n) {
        if (n <= 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        // 最优值
        int[] products = new int[n + 1];
        products[0] = 1;
        products[1] = 1;

        // 最优解的第一刀
        int[] solutions = new int[n + 1];
        solutions[0] = 1;
        solutions[1] = 1;

        // 自底向上求解
        for (int i = 2; i <= n; i++) {
            int max = 0;
            for (int j = 1; j <= i; j++) {
                int product = j * products[i - j];
                if (product > max) {
                    max = product;
                    solutions[i] = j;
                }
            }
            products[i] = max;
        }

        // 打印最优值和最优解
        for (int i = 0; i < products.length; i++) {
            String function = String.format("f(%s)=%s", i, products[i]);
            String solution = getSolution(i, solutions);
            System.out.println(String.format("%s【%s】", function, solution));
        }

        return products[n];
    }

    /**
     * 获取最优解
     */
    private static String getSolution(int n, int[] solutions) {
        if (n == 0) {
            return "";
        }

        if (n == 1) {
            return "1";
        }

        int first = solutions[n];
        int rest = n - first;

        if (rest == 0) {
            return String.valueOf(first);
        }

        return String.format("%s * %s", first, getSolution(rest, solutions));
    }

    /**
     * 求剪绳子方案的最大乘积（贪婪算法）
     * 贪婪选择：n>=5时尽可能多的剪长度为3的绳子，n=4时把绳子剪成两段长度为2的绳子。
     */
    public static int calculateMaxProductByGreedy(int n) {
        if (n <= 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        if (n == 3) {
            return 3;
        }

        // n>=5时尽可能多的剪长度为3的绳子
        int timesOf3 = n / 3;

        // n=4时把绳子剪成两段长度为2的绳子
        if (n - timesOf3 * 3 == 1) {
            timesOf3--;
        }

        int timesOf2 = (n - timesOf3 * 3) / 2;

        // 返回最优值
        return (int) (Math.pow(3, timesOf3) * Math.pow(2, timesOf2));
    }

    public static void main(String[] args) {
        // 动态规划，时间复杂度：O(n^2)，空间复杂度：O(n)
        calculateMaxProductByDP(10);
        // 贪婪算法：时间复杂度：O(1)，空间负责度：O(1)
        System.out.println(calculateMaxProductByGreedy(10));
    }
}

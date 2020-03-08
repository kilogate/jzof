package com.kilogate.jzof.chapter02.algorithm;

/**
 * 机器人的运动范围
 * <p>
 * 地上有一个m行n列的方格。
 * 一个机器人从坐标(0,0)的格子开始移动，它每次可以上下左右移动一格，但不能进入行列坐标数位之和大于k的格子。
 * 求该机器人一共能到达多少个格子。
 *
 * @author fengquanwei
 * @create 2020/3/8 下午9:41
 **/
public class RobotMoveRange {
    /**
     * 计算能达到的格子数（回溯法）
     */
    public static int calculateMoveCount(int m, int n, int k) {
        if (m < 1 || n < 1 || k < 0) {
            return 0;
        }

        boolean[][] visited = new boolean[m][n];

        int result = doCalculateMoveCount(m, n, 0, 0, k, visited);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(String.format("(%-2s,%-2s):%-5s\t", i, j, visited[i][j]));
            }
            System.out.println();
        }

        return result;
    }

    private static int doCalculateMoveCount(int m, int n, int r, int c, int k, boolean[][] visited) {
        if (r < 0 || r >= m || c < 0 || c >= n) {
            return 0;
        }

        if (visited[r][c]) {
            return 0;
        }

        if ((getDigitSum(r) + getDigitSum(c)) > k) {
            return 0;
        }

        visited[r][c] = true;

        int upCount = doCalculateMoveCount(m, n, r - 1, c, k, visited);
        int downCount = doCalculateMoveCount(m, n, r + 1, c, k, visited);
        int leftCount = doCalculateMoveCount(m, n, r, c - 1, k, visited);
        int rightCount = doCalculateMoveCount(m, n, r, c + 1, k, visited);

        return 1 + upCount + downCount + leftCount + rightCount;
    }

    private static int getDigitSum(int num) {
        int sum = 0;

        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(calculateMoveCount(101, 101, 18));
    }
}

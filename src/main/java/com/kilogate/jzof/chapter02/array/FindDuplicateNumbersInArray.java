package com.kilogate.jzof.chapter02.array;

import java.util.Arrays;

/**
 * 不修改数组找出重复的数字
 * <p>
 * 在一个长度为 n+1 的数组里，所有的数字都在 1 ~ n 的范围内，不修改数组找出重复的数字。
 *
 * @author fengquanwei
 * @create 2020/2/17 下午9:28
 **/
public class FindDuplicateNumbersInArray {
    /**
     * 时间复杂度：O(n)，空间复杂度：O(n)
     */
    public static int findDuplicateNumbers1(int[] numbers) {
        if (numbers == null) {
            return -1;
        }

        int length = numbers.length;

        if (length < 2) {
            return -1;
        }

        // 该方案需要 O(n) 的辅助空间
        int[] temps = new int[length];

        for (int i = 0; i < length; i++) {
            if (numbers[i] < 1 || numbers[i] >= length) {
                return -1;
            }

            if (temps[numbers[i]] != 0) {
                return numbers[i];
            } else {
                temps[numbers[i]] = numbers[i];
            }
        }

        return -1;
    }

    /**
     * 时间复杂度：O(nlogn)，空间复杂度：O(1)
     * getCountOfRange 被调用 O(logn)次，每次需要 O(n) 时间，总时间复杂度是 O(nlogn)。
     * 相比较于 findDuplicateNumbers1 是以时间换空间的策略。
     * 这种算法可以找到一个重复的数字，但可能无法找到所有的重复数字。例如 {2,3,5,4,3,2,6,7} 可以找到 3 但找不到 2。
     */
    public static int findDuplicateNumbers2(int[] numbers) {
        if (numbers == null) {
            return -1;
        }

        int length = numbers.length;

        if (length < 2) {
            return -1;
        }

        int left = 1;
        int right = length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (getCountOfRange(numbers, left, mid) > (mid - left + 1)) {
                right = mid;
            } else if (getCountOfRange(numbers, mid + 1, right) > (right - mid)) {
                left = mid + 1;
            } else {
                // 走到这里说明数据非法
                return -1;
            }

            if (left == right) {
                return left;
            }
        }

        return -1;
    }

    private static int getCountOfRange(int[] numbers, int left, int right) {
        if (numbers == null || numbers.length == 0) {
            return 0;
        }

        int count = 0;

        for (int number : numbers) {
            if (number >= left && number <= right) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] numbers1 = null;
        int[] numbers2 = {};
        int[] numbers3 = {1, 3, 5};
        int[] numbers4 = {1, 3, 4, 2, 4};
        int[] numbers5 = {1, 2, 5, 4, 2, 3};

        // findDuplicateNumbers1 是时间效率优先；findDuplicateNumbers2 是空间效率优先；
        System.out.println(String.format("%s findDuplicateNumbers1: %s", Arrays.toString(numbers1), FindDuplicateNumbersInArray.findDuplicateNumbers2(numbers1)));
        System.out.println(String.format("%s findDuplicateNumbers1: %s", Arrays.toString(numbers2), FindDuplicateNumbersInArray.findDuplicateNumbers2(numbers2)));
        System.out.println(String.format("%s findDuplicateNumbers1: %s", Arrays.toString(numbers3), FindDuplicateNumbersInArray.findDuplicateNumbers2(numbers3)));
        System.out.println(String.format("%s findDuplicateNumbers1: %s", Arrays.toString(numbers4), FindDuplicateNumbersInArray.findDuplicateNumbers2(numbers4)));
        System.out.println(String.format("%s findDuplicateNumbers1: %s", Arrays.toString(numbers5), FindDuplicateNumbersInArray.findDuplicateNumbers2(numbers5)));
    }
}

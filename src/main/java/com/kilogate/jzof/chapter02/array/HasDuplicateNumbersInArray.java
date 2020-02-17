package com.kilogate.jzof.chapter02.array;

import java.util.Arrays;

/**
 * 判断数组中是否有重复的数字
 * <p>
 * 在一个长度为 n 的数组里，所有的数字都在 0 ~ n-1 的范围内，判断数组中是否有重复的数字。
 *
 * @author fengquanwei
 * @create 2020/2/12 下午11:10
 **/
public class HasDuplicateNumbersInArray {
    public static boolean hasDuplicateNumbers(int[] numbers) {
        if (numbers == null) {
            return false;
        }

        int length = numbers.length;

        if (length == 0) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            if (numbers[i] >= length || numbers[i] < 0) {
                // 非法参数直接返回false
                return false;
            }
        }

        for (int i = 0; i < length; i++) {
            while (numbers[i] != i) {
                if (numbers[i] == numbers[numbers[i]]) {
                    return true;
                }

                int temp = numbers[i];
                numbers[i] = numbers[temp];
                numbers[temp] = temp;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] numbers1 = null;
        int[] numbers2 = {};
        int[] numbers3 = {1, 3, 5};
        int[] numbers4 = {1, 3, 5, 0, 2, 4};
        int[] numbers5 = {1, 3, 5, 0, 2, 3};

        System.out.println(String.format("%s hasDuplicateNumbers: %s", Arrays.toString(numbers1), hasDuplicateNumbers(numbers1)));
        System.out.println(String.format("%s hasDuplicateNumbers: %s", Arrays.toString(numbers2), hasDuplicateNumbers(numbers2)));
        System.out.println(String.format("%s hasDuplicateNumbers: %s", Arrays.toString(numbers3), hasDuplicateNumbers(numbers3)));
        System.out.println(String.format("%s hasDuplicateNumbers: %s", Arrays.toString(numbers4), hasDuplicateNumbers(numbers4)));
        System.out.println(String.format("%s hasDuplicateNumbers: %s", Arrays.toString(numbers5), hasDuplicateNumbers(numbers5)));
    }
}

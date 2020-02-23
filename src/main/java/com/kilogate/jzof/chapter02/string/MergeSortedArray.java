package com.kilogate.jzof.chapter02.string;

import java.util.Arrays;

/**
 * 合并已排序的数组
 * <p>
 * 有两个排序的数组A1和A2，请实现一个函数，把A1、A2中的所有数字插入新的数组A3中，并且所有的数字是排序的。
 *
 * @author fengquanwei
 * @create 2020/2/23 下午3:05
 **/
public class MergeSortedArray {
    public static int[] mergeSortedArray(int[] a1, int[] a2) {
        // 假设 a1，a2 已排序

        if (a1 == null || a1.length == 0) {
            return a2;
        }

        if (a2 == null || a2.length == 0) {
            return a1;
        }

        int[] result = new int[a1.length + a2.length];

        int index = 0;
        int index1 = 0;
        int index2 = 0;

        while (index1 < a1.length && index2 < a2.length) {
            if (a1[index1] <= a2[index2]) {
                result[index] = a1[index1];
                index++;
                index1++;
            } else {
                result[index] = a2[index2];
                index++;
                index2++;
            }
        }

        while (index1 < a1.length) {
            result[index] = a1[index1];
            index++;
            index1++;
        }

        while (index2 < a2.length) {
            result[index] = a2[index2];
            index++;
            index2++;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] a1 = {0, 2, 4, 6, 8};
        int[] a2 = {1, 3, 5, 7, 9, 11, 13};

        System.out.println(Arrays.toString(mergeSortedArray(a1, a2)));
    }
}

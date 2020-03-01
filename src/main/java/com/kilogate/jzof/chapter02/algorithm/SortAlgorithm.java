package com.kilogate.jzof.chapter02.algorithm;

import java.util.Arrays;

/**
 * 常见排序算法
 *
 * @author fengquanwei
 * @create 2020/3/1 下午5:30
 **/
public class SortAlgorithm {
    /**
     * 冒泡排序
     */
    public static void bubbleSort(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }

        int n = a.length;

        // 未发生交换时表示已排序完成
        boolean swap = true;

        // 每一趟冒出一个最大元素到尾部，需要 n-1 趟
        for (int i = 0; swap && i < n - 1; i++) {
            swap = false;

            // 从头冒到未排序的尾部的前一个元素
            for (int j = 0; j < n - i - 1; j++) {
                // 当前元素要小于后一个元素，否则交换位置
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;

                    swap = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {5, 4, 3, 2, 1};

        bubbleSort(a);
        System.out.println(Arrays.toString(a));
    }
}

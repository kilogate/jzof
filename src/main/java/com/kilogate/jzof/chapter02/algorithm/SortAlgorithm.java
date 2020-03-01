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
     * 一、冒泡排序
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

    /**
     * 二、选择排序
     */
    public static void selectionSort(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }

        int n = a.length;

        // 每一趟选出一个最小元素到头部，需要 n-1 趟
        for (int i = 0; i < n - 1; i++) {
            // 最小元素的下标
            int minIndex = i;

            // 从未排序的头部元素到尾部元素中选出最小元素
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }

            // 与未排序的头部元素交互
            if (minIndex != i) {
                int temp = a[i];
                a[i] = a[minIndex];
                a[minIndex] = temp;
            }
        }
    }

    /**
     * 三、插入排序
     */
    public static void insertionSort(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }

        int n = a.length;

        // 依次插入下一个未排序元素
        for (int i = 0; i < n - 1; i++) {
            // 当前未排序元素
            int current = a[i + 1];

            int preIndex = i;
            while (preIndex >= 0 && a[preIndex] > current) {
                a[preIndex + 1] = a[preIndex];
                preIndex--;
            }

            a[preIndex + 1] = current;
        }
    }

    /**
     * 四、希尔排序（一种插入排序，又称缩小增量排序）
     */
    public static void shellSort(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }

        int n = a.length;
        int gap = n / 2;

        while (gap > 0) {
            for (int i = gap; i < n; i++) {
                int current = a[i];
                int preIndex = i - gap;

                while (preIndex >= 0 && a[preIndex] > current) {
                    a[preIndex + gap] = a[preIndex];
                    preIndex -= gap;
                }

                a[preIndex + gap] = current;
            }

            gap /= 2;
        }
    }

    /**
     * 五、归并排序
     */

    /**
     * 六、快速排序
     */

    /**
     * 七、堆排序
     */

    /**
     * 八、计数排序
     */

    /**
     * 九、桶排序
     */

    /**
     * 十、基数排序
     */

    public static void main(String[] args) {
//        int[] a = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        shellSort(a);
        System.out.println(Arrays.toString(a));
    }
}

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

        // 未发生交换时表示已排序完成
        boolean swap = true;

        // 每一趟冒出一个最大元素到尾部，需要 n-1 趟
        for (int i = 0; swap && i < a.length - 1; i++) {
            swap = false;

            // 从头冒到未排序的尾部的前一个元素
            for (int j = 0; j < a.length - i - 1; j++) {
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

        // 每一趟选出一个最小元素到头部，需要 n-1 趟
        for (int i = 0; i < a.length - 1; i++) {
            // 最小元素的下标
            int minIndex = i;

            // 从未排序的头部元素到尾部元素中选出最小元素
            for (int j = i + 1; j < a.length; j++) {
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

        // 依次插入下一个未排序元素
        for (int i = 0; i < a.length - 1; i++) {
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

        int gap = a.length / 2;

        while (gap > 0) {
            for (int i = gap; i < a.length; i++) {
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
    public static int[] mergeSort(int[] a) {
        if (a == null || a.length <= 1) {
            return a;
        }

        int mid = a.length / 2;

        int[] left = Arrays.copyOfRange(a, 0, mid);
        int[] right = Arrays.copyOfRange(a, mid, a.length);

        return doMergeSort(mergeSort(left), mergeSort(right));
    }

    private static int[] doMergeSort(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];

        int leftIndex = 0;
        int rightIndex = 0;
        int resultIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] <= right[resultIndex]) {
                result[resultIndex] = left[leftIndex];
                resultIndex++;
                leftIndex++;
            } else {
                result[resultIndex] = right[rightIndex];
                resultIndex++;
                rightIndex++;
            }
        }

        while (leftIndex < left.length) {
            result[resultIndex] = left[leftIndex];
            resultIndex++;
            leftIndex++;
        }

        while (rightIndex < right.length) {
            result[resultIndex] = right[rightIndex];
            resultIndex++;
            rightIndex++;
        }

        return result;
    }

    /**
     * 六、快速排序
     */
    public static void quickSort(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }

        doQuickSort(a, 0, a.length - 1);
    }

    private static void doQuickSort(int[] a, int left, int right) {
        if (a == null || a.length <= 1 || left >= right) {
            return;
        }

        // 基准元素
        int p = a[left];

        int l = left;
        int r = right;

        while (l < r) {
            // 从右向左找小于基准元素的元素下标
            while (l < r && a[r] >= p) {
                r--;
            }

            a[l] = a[r];

            // 从左向右找大于基准元素的元素下标
            while (l < r && a[l] <= p) {
                l++;
            }

            a[r] = a[l];
        }

        // 基准元素归位
        a[l] = p;

        // 递归排序基准元素左侧序列
        doQuickSort(a, left, l - 1);

        // 递归排序基准元素右侧序列
        doQuickSort(a, r + 1, right);
    }

    /**
     * 七、堆排序
     */
    public static void heapSort(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }

        int length = a.length;

        // 构造最大堆
        buildMaxHeap(a);

        while (length > 1) {
            // 交换收尾元素
            int temp = a[0];
            a[0] = a[length - 1];
            a[length - 1] = temp;

            // 未排序数组长度减一
            length--;

            // 调整首元素使堆满足最大堆
            adjustMaxHeap(a, 0, length);
        }
    }

    private static void buildMaxHeap(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }

        // 自底向上自右向左构造最大堆
        for (int i = a.length - 1; i >= 0; i--) {
            adjustMaxHeap(a, i, a.length);
        }
    }

    /**
     * 调整元素使堆满足最大堆
     */
    private static void adjustMaxHeap(int[] a, int i, int length) {
        if (a == null || a.length <= 1) {
            return;
        }

        if (length < 1 || length > a.length) {
            return;
        }

        if (i < 0 || i >= length) {
            return;
        }


        int max = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // 左子树元素更大？
        if (left < length && a[left] > a[max]) {
            max = left;
        }

        // 右子树元素更大？
        if (right < length && a[right] > a[max]) {
            max = right;
        }

        // 当前元素不是最大元素？
        if (max != i) {
            // 与最大元素交换位置
            int temp = a[max];
            a[max] = a[i];
            a[i] = temp;

            // 继续调整次大元素
            adjustMaxHeap(a, max, length);
        }
    }

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
        int[] a = {5, 1, 7, 3, 1, 6, 9, 4};
//        int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        heapSort(a);
        System.out.println(Arrays.toString(a));
    }
}

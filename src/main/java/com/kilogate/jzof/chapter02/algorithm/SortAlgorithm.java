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
    public static void bubbleSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        // 未发生交换时表示已排序完成
        boolean swap = true;

        // 每一趟冒出一个最大元素到尾部，需要 n-1 趟
        for (int i = 0; swap && i < array.length - 1; i++) {
            swap = false;

            // 从头冒到未排序的尾部的前一个元素
            for (int j = 0; j < array.length - i - 1; j++) {
                // 当前元素要小于后一个元素，否则交换位置
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    swap = true;
                }
            }
        }
    }

    /**
     * 二、选择排序
     */
    public static void selectionSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        // 每一趟选出一个最小元素到头部，需要 n-1 趟
        for (int i = 0; i < array.length - 1; i++) {
            // 最小元素的下标
            int minIndex = i;

            // 从未排序的头部元素到尾部元素中选出最小元素
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            // 与未排序的头部元素交互
            if (minIndex != i) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }

    /**
     * 三、插入排序
     */
    public static void insertionSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        // 依次插入下一个未排序元素
        for (int i = 0; i < array.length - 1; i++) {
            // 当前未排序元素
            int current = array[i + 1];

            int preIndex = i;
            while (preIndex >= 0 && array[preIndex] > current) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }

            array[preIndex + 1] = current;
        }
    }

    /**
     * 四、希尔排序（一种插入排序，又称缩小增量排序）
     */
    public static void shellSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        int gap = array.length / 2;

        while (gap > 0) {
            for (int i = gap; i < array.length; i++) {
                int current = array[i];
                int preIndex = i - gap;

                while (preIndex >= 0 && array[preIndex] > current) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                }

                array[preIndex + gap] = current;
            }

            gap /= 2;
        }
    }

    /**
     * 五、归并排序
     */
    public static int[] mergeSort(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }

        int mid = array.length / 2;

        int[] leftArray = Arrays.copyOfRange(array, 0, mid);
        int[] rightArray = Arrays.copyOfRange(array, mid, array.length);

        return doMergeSort(mergeSort(leftArray), mergeSort(rightArray));
    }

    private static int[] doMergeSort(int[] leftArray, int[] rightArray) {
        int[] resultArray = new int[leftArray.length + rightArray.length];

        // leftArray index
        int l = 0;
        // rightArray index
        int r = 0;
        // resultArray index
        int s = 0;

        while (l < leftArray.length && r < rightArray.length) {
            if (leftArray[l] <= rightArray[s]) {
                resultArray[s] = leftArray[l];
                s++;
                l++;
            } else {
                resultArray[s] = rightArray[r];
                s++;
                r++;
            }
        }

        while (l < leftArray.length) {
            resultArray[s] = leftArray[l];
            s++;
            l++;
        }

        while (r < rightArray.length) {
            resultArray[s] = rightArray[r];
            s++;
            r++;
        }

        return resultArray;
    }

    /**
     * 六、快速排序
     */
    public static void quickSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        doQuickSort(array, 0, array.length - 1);
    }

    private static void doQuickSort(int[] array, int minIndex, int maxIndex) {
        if (array == null || array.length <= 1 || minIndex >= maxIndex) {
            return;
        }

        // 基准元素
        int pivot = array[minIndex];

        int l = minIndex;
        int r = maxIndex;

        while (l < r) {
            // 从右向左找小于基准元素的元素下标
            while (l < r && array[r] >= pivot) {
                r--;
            }

            array[l] = array[r];

            // 从左向右找大于基准元素的元素下标
            while (l < r && array[l] <= pivot) {
                l++;
            }

            array[r] = array[l];
        }

        // 基准元素归位
        array[l] = pivot;

        // 递归排序基准元素左侧序列
        doQuickSort(array, minIndex, l - 1);

        // 递归排序基准元素右侧序列
        doQuickSort(array, r + 1, maxIndex);
    }

    /**
     * 七、堆排序
     */
    public static void heapSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        int length = array.length;

        // 构造最大堆
        buildMaxHeap(array);

        while (length > 1) {
            // 交换收尾元素
            int temp = array[0];
            array[0] = array[length - 1];
            array[length - 1] = temp;

            // 未排序数组长度减一
            length--;

            // 调整首元素使堆满足最大堆
            adjustMaxHeap(array, 0, length);
        }
    }

    private static void buildMaxHeap(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        // 自底向上自右向左构造最大堆
        for (int i = array.length - 1; i >= 0; i--) {
            adjustMaxHeap(array, i, array.length);
        }
    }

    /**
     * 调整元素使堆满足最大堆
     */
    private static void adjustMaxHeap(int[] array, int index, int length) {
        if (array == null || array.length <= 1) {
            return;
        }

        if (length < 1 || length > array.length) {
            return;
        }

        if (index < 0 || index >= length) {
            return;
        }


        int max = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        // 左子树元素更大？
        if (left < length && array[left] > array[max]) {
            max = left;
        }

        // 右子树元素更大？
        if (right < length && array[right] > array[max]) {
            max = right;
        }

        // 当前元素不是最大元素？
        if (max != index) {
            // 与最大元素交换位置
            int temp = array[max];
            array[max] = array[index];
            array[index] = temp;

            // 继续调整次大元素
            adjustMaxHeap(array, max, length);
        }
    }

    /**
     * 八、计数排序
     */
    public static void countingSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        // 1 寻找最小值和最大值
        int min = array[0];
        int max = array[0];

        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }

            if (array[i] > max) {
                max = array[i];
            }
        }

        // 2 填充桶位
        int[] bucket = new int[max - min + 1];

        for (int i = 0; i < array.length; i++) {
            bucket[array[i] - min]++;
        }

        // 3 反向填充原数组
        int i = 0;
        int b = 0;

        while (i < array.length) {
            if (bucket[b] > 0) {
                array[i] = b + min;

                bucket[b]--;
                i++;
            } else {
                b++;
            }
        }
    }

    /**
     * 九、桶排序
     */

    /**
     * 十、基数排序
     */

    public static void main(String[] args) {
        int[] a = {5, 1, 7, 3, 1, 6, 9, 3, 4, 5, 4, 4};
        countingSort(a);
        System.out.println(Arrays.toString(a));
    }
}

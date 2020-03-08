package com.kilogate.jzof.chapter02.algorithm;

/**
 * 旋转数组的最小数字
 * <p>
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 *
 * @author fengquanwei
 * @create 2020/3/8 下午5:55
 **/
public class FindMinOfRotaryArray {
    public static int findMinOfRotaryArray(int[] array) {
        if (array == null || array.length == 0) {
            return -1;
        }

        if (array.length == 1) {
            return array[0];
        }

        // 假设输入的数组符合旋转数组规定

        // l：左边有序子数组的下标，r：右边有序子数组的下标，m：中间元素的下标
        int l = 0;
        int r = array.length - 1;

        // 特例一：旋转了0个元素，此时第一个元素是最小元素
        int m = 0;

        while (array[l] >= array[r] && r - l > 1) {
            m = (l + r) / 2;

            // 特例二：如果左边元素等于右边元素等于中间元素，无法确认中间元素是左边有序子数组的还是右边有序子数组的，此时改为顺序查找
            if (array[m] == array[l] && array[m] == array[r]) {
                return findMinOfRotaryArrayInOrder(array);
            }

            if (array[m] >= array[l]) {
                l = m;
            } else {
                r = m;
            }
        }

        return array[m];
    }

    private static int findMinOfRotaryArrayInOrder(int[] array) {
        int min = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }

        return min;
    }

    public static void main(String[] args) {
        System.out.println(findMinOfRotaryArray(new int[]{1, 0, 1, 1, 1}));
        System.out.println(findMinOfRotaryArray(new int[]{1, 1, 1, 0, 1}));
    }
}

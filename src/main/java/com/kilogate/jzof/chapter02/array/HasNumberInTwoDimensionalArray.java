package com.kilogate.jzof.chapter02.array;

import java.util.Arrays;

/**
 * 二维数组中的查找
 * <p>
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。判断某数字是否在该二维数组中。
 *
 * @author fengquanwei
 * @create 2020/2/18 下午11:36
 **/
public class HasNumberInTwoDimensionalArray {
    public static boolean hasNumberInTwoDimensionalArray(int[][] numbers, int target) {
        if (numbers == null || numbers.length == 0) {
            return false;
        }

        int rows = numbers.length;
        int columns = numbers[0].length;

        // 检查输入数据合法性：元素数检查
        for (int i = 0; i < rows; i++) {
            if (numbers[i].length != columns) {
                return false;
            }
        }

        // 检查输入数据合法性：递增检查
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // 向下要递增
                if (i < rows - 1 && numbers[i + 1][j] < numbers[i][j]) {
                    return false;
                }

                // 向右要递增
                if (j < columns - 1 && numbers[i][j + 1] < numbers[i][j]) {
                    return false;
                }
            }
        }

        int row = 0;
        int column = columns - 1;

        while (row < rows && column >= 0) {
            if (numbers[row][column] == target) {
                return true;
            } else if (numbers[row][column] > target) {
                column--;
            } else {
                row++;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] numbers1 = null;
        int[][] numbers2 = {};
        int[][] numbers3 = {{1, 2, 3}, {4, 5, 3}};
        int[][] numbers4 = {{1, 2, 3}, {4, 5}, {6, 7, 8}};
        int[][] numbers5 = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};

        int target = 4;

        System.out.println(String.format("array: %s, target: %s, hasNumberInTwoDimensionalArray: %s", Arrays.deepToString(numbers1), target, HasNumberInTwoDimensionalArray.hasNumberInTwoDimensionalArray(numbers1, target)));
        System.out.println(String.format("array: %s, target: %s, hasNumberInTwoDimensionalArray: %s", Arrays.deepToString(numbers2), target, HasNumberInTwoDimensionalArray.hasNumberInTwoDimensionalArray(numbers2, target)));
        System.out.println(String.format("array: %s, target: %s, hasNumberInTwoDimensionalArray: %s", Arrays.deepToString(numbers3), target, HasNumberInTwoDimensionalArray.hasNumberInTwoDimensionalArray(numbers3, target)));
        System.out.println(String.format("array: %s, target: %s, hasNumberInTwoDimensionalArray: %s", Arrays.deepToString(numbers4), target, HasNumberInTwoDimensionalArray.hasNumberInTwoDimensionalArray(numbers4, target)));
        System.out.println(String.format("array: %s, target: %s, hasNumberInTwoDimensionalArray: %s", Arrays.deepToString(numbers5), target, HasNumberInTwoDimensionalArray.hasNumberInTwoDimensionalArray(numbers5, target)));
    }
}

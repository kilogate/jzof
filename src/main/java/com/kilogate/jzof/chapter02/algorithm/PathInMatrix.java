package com.kilogate.jzof.chapter02.algorithm;

/**
 * 矩阵中的路径
 * <p>
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向上下左右移动一格。
 * 如果一条路径经过了矩阵中的某一格，那么该路径不能再次进入该格子。
 *
 * @author fengquanwei
 * @create 2020/3/8 下午8:39
 **/
public class PathInMatrix {
    public static boolean hasPathInMatrix(char[] matrix, int rows, int columns, String path) {
        // 空路径肯定有
        if (path == null || path.length() == 0) {
            return true;
        }

        // 空矩阵肯定找不到路径
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        // 矩阵元素数错误
        int length = rows * columns;
        if (matrix.length != length) {
            return false;
        }

        // 遍历矩阵元素开始匹配第一个路径元素
        boolean[] visited = new boolean[matrix.length];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (doHasPathInMatrix(matrix, rows, columns, i, j, path, 0, visited)) {
                    // 找到路径
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean doHasPathInMatrix(char[] matrix, int rows, int columns, int row, int column, String path, int pathIndex, boolean[] visited) {
        // 不存在当前矩阵元素
        if (row < 0 || row >= rows || column < 0 || column >= columns) {
            return false;
        }

        int matrixIndex = row * columns + column;

        // 当前矩阵元素已访问过
        if (visited[matrixIndex]) {
            return false;
        }

        // 矩阵元素不匹配路径元素
        if (matrix[matrixIndex] != path.charAt(pathIndex)) {
            return false;
        }

        // 矩阵元素匹配了路径元素
        visited[matrixIndex] = true;

        // 矩阵元素匹配的是最后一个路径元素
        if (pathIndex == path.length() - 1) {
            return true;
        }

        // 看当前矩阵元素的上下左右元素是否能匹配到下一个路径元素
        boolean hasPath = doHasPathInMatrix(matrix, rows, columns, row - 1, column, path, pathIndex + 1, visited)
                || doHasPathInMatrix(matrix, rows, columns, row + 1, column, path, pathIndex + 1, visited)
                || doHasPathInMatrix(matrix, rows, columns, row, column - 1, path, pathIndex + 1, visited)
                || doHasPathInMatrix(matrix, rows, columns, row, column + 1, path, pathIndex + 1, visited);

        // 没有找到路径
        if (!hasPath) {
            visited[matrixIndex] = false;
        }

        return hasPath;
    }

    public static void main(String[] args) {
        char[] matrix = new char[]{
                'a', 'b', 't', 'g',
                'c', 'f', 'c', 's',
                'j', 'd', 'e', 'h'
        };

        System.out.println(hasPathInMatrix(matrix, 3, 4, "bfce"));
    }
}

package com.kilogate.jzof.chapter02.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 重建二叉树
 * <p>
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 *
 * @author fengquanwei
 * @create 2020/2/26 下午10:28
 **/
public class RebuildBinaryTree {
    public static Node getRootNode(Integer[] preOrder, Integer[] inOrder) {
        if (preOrder == null || preOrder.length == 0 || inOrder == null || inOrder.length == 0) {
            return null;
        }

        // 前序遍历结果与中序遍历结果元素数不同
        if (preOrder.length != inOrder.length) {
            return null;
        }

        // 只有一个元素
        if (preOrder.length == 1) {
            return new Node(preOrder[0]);
        }

        // 1 根元素
        int root = preOrder[0];
        Node result = new Node(root);

        // 2 获取左子树和右子树的中序遍历结果
        Pair<Integer[]> inOrderPair = getInOrderPair(inOrder, root);

        if (inOrderPair == null) {
            return result;
        }

        // 3 获取左子树和右子树前序遍历结果
        Pair<Integer[]> preOrderPair = getPreOrderPair(preOrder, inOrderPair.getLeft().length);

        if (preOrderPair == null) {
            return result;
        }

        // 4 递归设置左右子树
        result.setLeft(getRootNode(preOrderPair.getLeft(), inOrderPair.getLeft()));
        result.setRight(getRootNode(preOrderPair.getRight(), inOrderPair.getRight()));

        return result;
    }

    private static Pair<Integer[]> getInOrderPair(Integer[] inOrder, int root) {
        if (inOrder == null || inOrder.length == 0) {
            return null;
        }

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        boolean leftElement = true;

        for (int element : inOrder) {
            if (element == root) {
                leftElement = false;
                continue;
            }

            if (leftElement) {
                left.add(element);
            } else {
                right.add(element);
            }
        }

        return new Pair<>(left.toArray(new Integer[0]), right.toArray(new Integer[0]));
    }

    private static Pair<Integer[]> getPreOrderPair(Integer[] preOrder, int leftElementCount) {
        if (preOrder == null || preOrder.length == 0) {
            return null;
        }

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int i = 1; i < preOrder.length; i++) {
            if (i <= leftElementCount) {
                left.add(preOrder[i]);
            } else {
                right.add(preOrder[i]);
            }
        }

        return new Pair<>(left.toArray(new Integer[0]), right.toArray(new Integer[0]));
    }

    public static void main(String[] args) {
        Integer[] preOrder = {1, 2, 4, 7, 3, 5, 6, 8};
        Integer[] inOrder = {4, 7, 2, 1, 5, 3, 8, 6};

        Node rootNode = getRootNode(preOrder, inOrder);

        TraverseBinaryTree.DLR1(rootNode);
        System.out.println();
        TraverseBinaryTree.LDR1(rootNode);
    }
}

package com.kilogate.jzof.chapter02.tree;

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
    public static Node rebuildBinaryTree(int[] preOrder, int[] inOrder) {
        if (preOrder == null || preOrder.length == 0 || inOrder == null || inOrder.length == 0) {
            return null;
        }

        return doRebuildBinaryTree(preOrder, 0, preOrder.length - 1, inOrder, 0, inOrder.length - 1);
    }

    private static Node doRebuildBinaryTree(int[] preOrder, int preStart, int preEnd, int[] inOrder, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        int root = preOrder[preStart];

        Node result = new Node(root);

        for (int i = inStart; i <= inEnd; i++) {
            if (inOrder[i] == root) {
                int leftCount = i - inStart;
                result.setLeft(doRebuildBinaryTree(preOrder, preStart + 1, preStart + leftCount, inOrder, inStart, i - 1));
                result.setRight(doRebuildBinaryTree(preOrder, preStart + leftCount + 1, preEnd, inOrder, i + 1, inEnd));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] preOrder = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] inOrder = {4, 7, 2, 1, 5, 3, 8, 6};

        Node rootNode = rebuildBinaryTree(preOrder, inOrder);

        TraverseBinaryTree.DLR1(rootNode);
        System.out.println();
        TraverseBinaryTree.LDR1(rootNode);
    }
}

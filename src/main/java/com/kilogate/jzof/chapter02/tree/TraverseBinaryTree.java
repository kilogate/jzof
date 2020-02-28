package com.kilogate.jzof.chapter02.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 遍历二叉树
 *
 * @author fengquanwei
 * @create 2020/2/23 下午4:20
 **/
public class TraverseBinaryTree {
    /**
     * 前序遍历递归实现
     */
    public static void DLR1(Node node) {
        if (node == null) {
            return;
        }

        // 根
        System.out.print(node.getData() + "\t");
        // 左
        DLR1(node.getLeft());
        // 右
        DLR1(node.getRight());
    }

    /**
     * 前序遍历循环实现
     */
    public static void DLR2(Node node) {
        if (node == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            Node current = stack.pop();

            System.out.print(current.getData() + "\t");

            if (current.getRight() != null) {
                stack.push(current.getRight());
            }

            if (current.getLeft() != null) {
                stack.push(current.getLeft());
            }
        }
    }

    /**
     * 中序遍历递归实现
     */
    public static void LDR1(Node node) {
        if (node == null) {
            return;
        }

        // 左
        LDR1(node.getLeft());
        // 根
        System.out.print(node.getData() + "\t");
        // 右
        LDR1(node.getRight());
    }

    /**
     * 中序遍历循环实现
     */
    public static void LDR2(Node node) {
        if (node == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();

        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.getLeft();
            } else {
                node = stack.pop();
                System.out.print(node.getData() + "\t");
                node = node.getRight();
            }
        }
    }

    /**
     * 后序遍历递归实现
     */
    public static void LRD1(Node node) {
        if (node == null) {
            return;
        }

        // 左
        LRD1(node.getLeft());
        // 右
        LRD1(node.getRight());
        // 根
        System.out.print(node.getData() + "\t");
    }

    /**
     * 后序遍历循环实现
     */
    public static void LRD2(Node node) {
        if (node == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(node);

        Node last = node;

        while (!stack.isEmpty()) {
            Node current = stack.peek();

            if (current.getLeft() != null && last != current.getLeft() && last != current.getRight()) {
                stack.push(current.getLeft());
            } else if (current.getRight() != null && last != current.getRight()) {
                stack.push(current.getRight());
            } else {
                last = stack.pop();
                System.out.print(last.getData() + "\t");
            }
        }
    }

    /**
     * 层次遍历
     */
    public static void printByLevel(Node node) {
        if (node == null) {
            return;
        }

        Queue<Node> list = new LinkedList<>();
        list.add(node);

        while (!list.isEmpty()) {
            Node current = list.poll();

            System.out.print(current.getData() + "\t");

            if (current.getLeft() != null) {
                list.add(current.getLeft());
            }

            if (current.getRight() != null) {
                list.add(current.getRight());
            }
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(10);
        Node node2 = new Node(6);
        Node node3 = new Node(14);
        Node node4 = new Node(4);
        Node node5 = new Node(8);
        Node node6 = new Node(12);
        Node node7 = new Node(16);

        node1.setLeft(node2);
        node1.setRight(node3);

        node2.setLeft(node4);
        node2.setRight(node5);

        node3.setLeft(node6);
        node3.setRight(node7);

        DLR1(node1);
        System.out.println();

        DLR2(node1);
        System.out.println();

        LDR1(node1);
        System.out.println();

        LDR2(node1);
        System.out.println();

        LRD1(node1);
        System.out.println();

        LRD2(node1);
        System.out.println();

        printByLevel(node1);
    }
}

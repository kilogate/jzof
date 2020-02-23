package com.kilogate.jzof.chapter02.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * 从尾到头打印链表
 *
 * @author fengquanwei
 * @create 2020/2/23 下午3:55
 **/
public class PrintListReversal {
    /**
     * 用递归实现（链表不能过长否则递归调用层级过深会导致调用栈溢出）
     */
    public static void printListReversal1(Node node) {
        if (node == null) {
            return;
        }

        printListReversal1(node.getNext());

        System.out.print(node.getData() + "\t");
    }

    /**
     * 用循环实现
     */
    public static void printListReversal2(Node node) {
        if (node == null) {
            return;
        }

        List<Integer> datas = new ArrayList<>();

        while (node != null) {
            datas.add(node.getData());
            node = node.getNext();
        }

        for (int i = datas.size() - 1; i >= 0; i--) {
            System.out.print(datas.get(i) + "\t");
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);

        printListReversal2(node1);
    }
}

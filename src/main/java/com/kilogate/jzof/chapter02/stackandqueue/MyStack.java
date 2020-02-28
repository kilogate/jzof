package com.kilogate.jzof.chapter02.stackandqueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用两个队列实现栈
 *
 * @author fengquanwei
 * @create 2020/2/28 下午10:09
 **/
public class MyStack<E> {
    private Queue<E> queue1;
    private Queue<E> queue2;

    public MyStack() {
        this.queue1 = new LinkedList<>();
        this.queue2 = new LinkedList<>();
    }

    public void push(E e) {
        if (queue1.isEmpty()) {
            queue2.offer(e);
        } else {
            queue1.offer(e);
        }
    }

    public E pop() {
        if (!queue1.isEmpty()) {
            while (queue1.size() > 1) {
                queue2.offer(queue1.poll());
            }

            return queue1.poll();
        }

        if (!queue2.isEmpty()) {
            while (queue2.size() > 1) {
                queue1.offer(queue2.poll());
            }

            return queue2.poll();
        }

        return null;
    }

    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();

        stack.push(1);
        stack.push(2);

        System.out.println(stack.pop());

        stack.push(3);
        stack.push(4);
        stack.push(5);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}

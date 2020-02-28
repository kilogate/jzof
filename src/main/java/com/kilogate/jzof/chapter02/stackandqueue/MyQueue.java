package com.kilogate.jzof.chapter02.stackandqueue;

import java.util.Stack;

/**
 * 用两个栈实现队列
 *
 * @author fengquanwei
 * @create 2020/2/28 下午10:09
 **/
public class MyQueue<E> {
    /**
     * stack1 用于入队
     */
    private Stack<E> stack1;
    /**
     * stack2 用于出队
     */
    private Stack<E> stack2;

    public MyQueue() {
        this.stack1 = new Stack<>();
        this.stack2 = new Stack<>();
    }

    /**
     * 入队
     */
    public void offer(E e) {
        stack1.push(e);
    }

    /**
     * 出队
     */
    public E poll() {
        if (!stack2.isEmpty()) {
            return stack2.pop();
        }

        if (stack1.isEmpty()) {
            return null;
        }

        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }

        return stack2.pop();
    }

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue();

        queue.offer(1);
        queue.offer(2);

        System.out.println(queue.poll());

        queue.offer(3);
        queue.offer(4);

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}

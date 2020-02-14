package com.kilogate.jzof.chapter02.singleton;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Holder模式
 * <p>
 * 优点：将懒加载和线程安全完美结合的一种方式。
 *
 * @author fengquanwei
 * @create 2020/2/13 下午11:36
 **/
public class Singleton5 {
    private static class SingletonHolder {
        private static Singleton5 singleton = new Singleton5();
    }

    private Singleton5() {
    }

    public static Singleton5 getSingleton() {
        return SingletonHolder.singleton;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int threadNum = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    System.out.println(String.format("%s [%s] %s", System.currentTimeMillis(), Thread.currentThread(), Singleton5.getSingleton()));
                }

                countDownLatch.countDown();
            }).start();
        }

        try {
            countDownLatch.await(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(String.format("========== cost: %s ==========", System.currentTimeMillis() - start));
    }
}

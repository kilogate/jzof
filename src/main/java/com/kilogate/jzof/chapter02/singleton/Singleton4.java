package com.kilogate.jzof.chapter02.singleton;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 饿汉模式
 * <p>
 * 优点：饿汉模式天生是线程安全的，使用时没有延迟。
 * 缺点：启动时即创建实例，启动慢，有可能造成资源浪费。
 *
 * @author fengquanwei
 * @create 2020/2/13 下午11:36
 **/
public class Singleton4 {
    private static Singleton4 singleton = new Singleton4();

    private Singleton4() {
    }

    public static Singleton4 getSingleton() {
        return singleton;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int threadNum = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    System.out.println(String.format("%s [%s] %s", System.currentTimeMillis(), Thread.currentThread(), Singleton4.getSingleton()));
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

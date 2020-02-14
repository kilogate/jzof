package com.kilogate.jzof.chapter02.singleton;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 懒汉模式
 * <p>
 * 优点：懒加载启动快，资源占用小，使用时才实例化，无锁。
 * 缺点：非线程安全。
 *
 * @author fengquanwei
 * @create 2020/2/13 下午11:34
 **/
public class Singleton1 {
    private static Singleton1 singleton = null;

    private Singleton1() {
    }

    public static Singleton1 getSingleton() {
        if (singleton == null) {
            // 模拟初始化资源耗时
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            singleton = new Singleton1();
        }

        return singleton;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int threadNum = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        // 不支持多线程
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    System.out.println(String.format("%s [%s] %s", System.currentTimeMillis(), Thread.currentThread(), Singleton1.getSingleton()));
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

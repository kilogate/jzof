package com.kilogate.jzof.chapter02.singleton;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 线程安全的懒汉模式（Double Check）
 * <p>
 * 优点：懒加载启动快，资源占用小，使用时才实例化，性能好。
 * 缺点：实现复杂。
 *
 * @author fengquanwei
 * @create 2020/2/13 下午11:35
 **/
public class Singleton3 {
    private static volatile Singleton3 singleton = null;

    private Singleton3() {
    }

    public static Singleton3 getSingleton() {
        if (singleton == null) {
            synchronized (Singleton3.class) {
                if (singleton == null) {
                    // 模拟初始化资源耗时
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    singleton = new Singleton3();
                }
            }
        }

        return singleton;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int threadNum = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    System.out.println(String.format("%s [%s] %s", System.currentTimeMillis(), Thread.currentThread(), Singleton3.getSingleton()));
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

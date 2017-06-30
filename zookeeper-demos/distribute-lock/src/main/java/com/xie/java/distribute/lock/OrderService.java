package com.xie.java.distribute.lock;

import sun.misc.Unsafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xieyang on 6/17/17.
 */
public class OrderService implements Runnable {
    private OrderNumGenerator ong = new OrderNumGenerator();

    private static int threadCount = 100;

    private static Object mutex = new Object();
    //private Lock lock = new ZookeeperDistribureLock();
    private Lock lock = new ZookeeperImprovetLock();
    static CountDownLatch cdl = new CountDownLatch(threadCount);
    static AtomicInteger atomicInteger = new AtomicInteger();


    public void run() {
        try {
            getOrderRemote();
            //getOrderLocal();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getOrderLocal() {
        synchronized (mutex) {
            String orderNum = ong.getOrderNumber();
            System.out.println(Thread.currentThread().getName() + "获取得订单号:" + orderNum);
        }

    }

    public void getOrderRemote() {
        lock.getLock();
        String orderNum = ong.getOrderNumber();
        System.out.println(Thread.currentThread().getName() + "获取得订单号:" + orderNum);
        lock.unLock();

    }


    public static void main(String[] args) {
        for (int i = 0; i < threadCount; i++) {
            //
            new Thread((new OrderService())).start();

        }


        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

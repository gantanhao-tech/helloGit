package com.ylx.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author yanglx
 * @version 1.0
 * @date 2020-05-26
 */
public class SemaphoreDemo
{

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(3, true);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t停进来了");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + "\t停车3秒离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();

                }
            },String.valueOf(i)).start();
        }


    }
}

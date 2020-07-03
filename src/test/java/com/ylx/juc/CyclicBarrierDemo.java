package com.ylx.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author yanglx
 * @version 1.0
 * @date 2020-05-25
 */
public class CyclicBarrierDemo {



    public static void main(String[] args) {


        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () ->{
            System.out.println("人到齐了，可以开会");
        });

        for (int i = 1; i <= 7; i++) {
            int temp = i;
            new Thread(() -> {

                try {
                    System.out.println(Thread.currentThread().getName() + "\t第" + String.valueOf(temp));
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}

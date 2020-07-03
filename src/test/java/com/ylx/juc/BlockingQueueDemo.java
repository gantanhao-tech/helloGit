package com.ylx.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yanglx
 * @version 1.0
 * @date 2020-05-28
 */
class MyResourceFactory {

    private volatile boolean flag = true;//默认是ture，蛋糕生产标记
    private AtomicInteger atomicInteger = new AtomicInteger();

    private BlockingQueue<String> blockingQueue = null;

    public MyResourceFactory(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void prod() throws  Exception {

        String number = null;
        boolean offer;
        while (flag){
            number = atomicInteger.incrementAndGet() + "";
            offer = blockingQueue.offer(number, 2L, TimeUnit.SECONDS);
            if (offer){
                System.out.println(Thread.currentThread().getName() + "\t " + number + "插入成功");
            }else {
                System.out.println(Thread.currentThread().getName() + "\t " + number + "插入失败");
            }
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        }

        System.out.println(Thread.currentThread().getName() + "\t 大老板叫停了");
    }

    public void consumer() throws Exception {

        String result = null;
        while (flag){
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (result == null || result == ""){
                flag = false;
                System.out.println(Thread.currentThread().getName() + "\t 消费失败");
                System.out.println();
                System.out.println();
                return;
            }
            try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + "\t " + result + "消费成功");
            System.out.println();
            System.out.println();

        }

    }

    public void stop(){
        flag = false;
        System.out.println("强制叫停了、、、");
    }
}

/*
volatile/CAS/AtomicInteger/BlockingQueue,多线程
 */
public class BlockingQueueDemo {


    public static void main(String[] args) {

        MyResourceFactory factory = new MyResourceFactory(new ArrayBlockingQueue(10));

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\t 生产线程启栋");
                factory.prod();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"ThreadProd").start();
        
        new Thread(() -> {
            try {
                factory.consumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"ThreadConsumer").start();


        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }

        factory.stop();

    }
}

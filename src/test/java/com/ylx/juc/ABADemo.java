package com.ylx.juc;

import java.time.ZonedDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author yanglx
 * @version 1.0
 * @date 2020-05-23
 */
public class ABADemo {

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);


    public static void main(String[] args) {
        System.out.println("=======================ABA问题产生========================");
        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101,100);
            System.out.println(Thread.currentThread().getName() + "\t "+ atomicReference.get());
        },"A").start();

        new Thread(() -> {

            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            atomicReference.compareAndSet(100, 2019);
            System.out.println(Thread.currentThread().getName() + "\t " + atomicReference.get());
        },"B").start();

        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("=======================ABA问题解决========================");



        new Thread(() -> {

            int stamp = atomicStampedReference.getStamp();
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            atomicStampedReference.compareAndSet(100, 101, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + "\tvalue:" + atomicStampedReference.getReference() + "\tversion:" + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(), atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + "\tvalue:" + atomicStampedReference.getReference() + "\tversion:" + atomicStampedReference.getStamp());

        },"C").start();
        
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\tvalue:" + atomicStampedReference.getReference() + "\tversion:"+stamp);
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            boolean b = atomicStampedReference.compareAndSet(100, 2019, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + "\tvalue:" + atomicStampedReference.getReference() + "\tversion:"+atomicStampedReference.getStamp());
            System.out.println("result==" + b);
        },"D").start();

    }
    

}

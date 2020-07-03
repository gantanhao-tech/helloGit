package com.ylx.juc;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yanglx
 * @version 1.0
 * @date 2020-05-22
 */
public class VolatileMain {

   static class MyData{

        volatile int number = 0;
        String mapStr = "OK";

        public void addto60(){
            number= 60;
        }

        //不具有原子性
        public void numberPlus(){
            number++;
        }

       AtomicInteger atomicInteger = new AtomicInteger();

       public void addMyAtomic() {
           atomicInteger.getAndIncrement();
       }


       volatile HashMap<String,String> map = new HashMap<>();
       public MyData(){
           map.put("a", "A");
       }
       public void addMap(){
            map.put(50+"", 50+"");
       }

       public void removeMap(){
           mapStr = map.get("a");
           map.remove("a");

       }


   }

    /**
     * volatile 是轻量级的一种同步机制 ,单例模式下使用volatile修饰保证实例一次
     * 1.可见性
     * 2不具有原子性 ，使用AtomticInteger --> CAS->unsafe->CAS自旋 比较交换 getAndIncrement(),使用到了unsafe类和offsetValue主内存位置偏移量，
     * unsafe是rt.jar包下的一个类，通过他可以调用本地方法，在执行系统本地指令时，只能有一个线程，保证线程安全，内部通过比较从
     * 主内存上拷贝的数据快照和现在主内存上指定位置偏移量上的数据是否一致，一致则更新数据，否则继续循环，直到更新成功结束。
     * 当循环比较次数过多，也会造成CPU消耗较大，也会造成 ABA 问题 ？--->
     * 3.禁止指令重排 （编译阶段指令重排，优化执行顺序，在多线程下引发线程不安全）
     *
     *
     */
    public static void main(String[] args) {

        MyData myData = new MyData();
        seeOkTest(myData);
//        test();
    }


    public static void test(){
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println("now = " + now);
    }

    private static void atomicTest(MyData myData) {
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 2000; j++) {
                    myData.numberPlus();
                    myData.addMyAtomic();
                }
//                try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            },String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2){
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() +" number value 已经被修改为 " + myData.number);
        System.out.println(Thread.currentThread().getName() +" AtomticNumber value 已经被修改为 " + myData.atomicInteger);
    }

    private static void seeOkTest(MyData myData) {
        new Thread(() -> {
            System.out.println("start...");
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            myData.removeMap();
            System.out.println("当前线程 " + Thread.currentThread().getName() + " \tnumber value:" + myData.map + "\t" + myData.mapStr);
            System.out.println("end....");
        },"A").start();

        while (!myData.map.keySet().isEmpty()){

        }

        System.out.println(Thread.currentThread().getName() +" number value 已经被修改" + myData.map + "\t" + myData.mapStr);
    }


}

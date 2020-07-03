package com.ylx.juc;

/**
 * @author yanglx
 * @version 1.0
 * @date 2020-05-27
 */
class MyResource implements Runnable{
    private String lockA;
    private String lockB;

    public MyResource(String lockA,String lockB)
    {
        this.lockA = lockA;
        this.lockB = lockB;
    }


    @Override
    public void run() {


        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t 自己持有：" +lockA + "尝试获得:" + lockB);
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + "\t 自己持有：" +lockB + "尝试获得:" + lockA);
            }
        }

    }
}

public class DeadLockDemo {

    public static void main(String[] args) {

        String lockA = "lockA";
        String lockB = "lockB";


        new Thread(new MyResource(lockA, lockB),"ThreadAAA").start();
        new Thread(new MyResource(lockB, lockA),"ThreadBBB").start();
    }
}

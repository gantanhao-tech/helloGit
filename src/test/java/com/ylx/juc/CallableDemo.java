package com.ylx.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author yanglx
 * @version 1.0
 * @date 2020-05-22
 */

class MyTask implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t come in here" );

        try { TimeUnit.SECONDS.sleep(4); } catch (InterruptedException e) { e.printStackTrace(); }

        return 1024;
    }
}
//**
/*
创建线程的第3种方式 实现Callable接口

 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        FutureTask futureTask = new FutureTask(new MyTask());
        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start();

        System.out.println("......");
        System.out.println(Thread.currentThread().getName() + "  1\t" +futureTask.get());
        System.out.println(Thread.currentThread().getName() + "  2\t" +futureTask.get());


    }
}

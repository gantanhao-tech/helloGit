package com.ylx.juc;

import lombok.SneakyThrows;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yanglx
 * @version 1.0
 * @date 2020-05-13
 */

class Ticket {

    Integer number = 0;
    ReentrantLock  lock = new ReentrantLock();

    public void saleTicket(){
        lock.lock();
        try {
            if (number > 0){
                System.out.println(Thread.currentThread().getName() + "卖票:" + number + ",剩余：" + number--);
            }else {
                System.out.println("票已售完");
            }
        } finally{
            lock.unlock();
        }

    }

    public synchronized void increment() throws InterruptedException {

        while (number != 0){
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "***   " + number);
        this.notifyAll();
    }


    public synchronized void decrement() throws InterruptedException {

        while (number == 0){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "***  " + number);
        this.notifyAll();
    }



}
public class SaleTicketMain {

    public static void main(String[] args) {

        Ticket ticket = new Ticket();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    ticket.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    ticket.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    ticket.increment();

                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    ticket.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
//        new Thread(()->{for (int i = 0; i < 40; i++) ticket.saleTicket();},"A").start();
//        new Thread(()->{for (int i = 0; i < 40; i++) ticket.saleTicket();},"B").start();


    }


}

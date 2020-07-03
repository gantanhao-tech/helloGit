package com.ylx.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author yanglx
 * @version 1.0
 * @date 2020-05-29
 */

class MyRe implements Runnable{
    private String lockA;

    public MyRe(String lockA ) {
        this.lockA = lockA;
    }


    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + "\t " + lockA + "你好。。");
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() + "\t==============");
            try { TimeUnit.SECONDS.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
        }

        System.out.println(Thread.currentThread().getName() + "\t " + lockA + "拜拜。。");
    }
}
public class jucTest {

    public static void main(String[] args) {

        String lockA = "lockA";
        String lockB = "lockB";
        System.out.println("验证：" +" sdas");
        HashMap<String, String> map = new HashMap<>();
        map.put(lockA , lockA);
        map.put(lockB , lockB);

        String s1 = map.get(lockA);
        String s2 = map.get(lockA);

        System.out.println("ssssssss" + map.get("a"));

        new Thread(new MyRe(s1),"ThreadAAA").start();
        new Thread(new MyRe(s2),"ThreadBBB").start();
    }


}

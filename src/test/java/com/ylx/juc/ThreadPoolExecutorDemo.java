package com.ylx.juc;


import cn.hutool.extra.emoji.EmojiUtil;
import com.vdurmont.emoji.Emoji;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author yanglx
 * @version 1.0
 * @date 2020-05-27
 */

/*
 4.线程池技术创建线程的第4种方式
 */
public class ThreadPoolExecutorDemo {


    public static void main(String[] args) {

        String alias = EmojiUtil.toAlias("😄");//:smile:

        Emoji emoji = EmojiUtil.get(alias);
        System.out.println("emoji = " + emoji.getUnicode());
    }

    private static void threadPoolTest() {
        ExecutorService executorPool1 = Executors.newFixedThreadPool(3);//固定数
        ExecutorService executorPool2 = Executors.newSingleThreadExecutor();//单个线程
        ExecutorService executorPool3 = Executors.newCachedThreadPool();//可变长

        int cpuNum = Runtime.getRuntime().availableProcessors();
        System.out.println(cpuNum);

        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 1; i <= 9; i++) {
            threadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "\t办理业务");
            });
        }
    }


}

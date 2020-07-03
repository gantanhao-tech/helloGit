package com.ylx.jvm;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author yanglx
 * @version 1.0
 * @date 2020-06-09
 */
public class StringIntern {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        for (int i = 0; i < 10000000; i++) {
            int a = i % 10;
            String s = String.valueOf(integers.get(a));
//            String.valueOf(integers.get(a)).intern();
        }

        long end = System.currentTimeMillis();

        System.out.println("耗时：" + String.valueOf(end-start) + "ms");

//        try { TimeUnit.SECONDS.sleep(100000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    private static void test2() {
        String s = new String("a") + 1;
        s.intern();
        String s1 = "a1";
        System.out.println(s==s1);
    }

    private static void test1() {
        String s = "a" +"b";
        String s1 = "a";
        String s2 = s1 + "b";

        System.out.println(s == s2);
    }
}

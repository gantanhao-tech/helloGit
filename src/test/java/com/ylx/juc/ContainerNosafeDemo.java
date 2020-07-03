package com.ylx.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author yanglx
 * @version 1.0
 * @date 2020-05-23
 */
public class ContainerNosafeDemo {

    /*
    集合类线程不安全
     */
    public static void main(String[] args) {

        Map<String, Object> map = new ConcurrentHashMap<String,Object>();
    }

    private static void setDEmo() {
        HashSet<String> sets = new HashSet<>();
        Set<String> strings = Collections.synchronizedSet(sets);
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                strings.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(strings);
            },String.valueOf(i)).start();
        }
    }

    private static void arrayListTest() {
        Collections.synchronizedList(new ArrayList<>());

        List<String> list = new ArrayList<>();

        List<String> writeArrayList = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}

package com.ylx.jvm;

import jdk.internal.org.objectweb.asm.Opcodes;

import java.util.ArrayList;

/**
 * @author yanglx
 * @version 1.0
 * @date 2020-05-18
 */

public class LogGCMain {


    /*
        Serial Parallel CMS G1
        Serial Parallel CMS G1 
     */
    /**
     * 设置堆大小
     * @param args
     */
    public static void main(String[] args) {

        int i = 1;
        try {
            String ss = "com.ylx.spring";
            ArrayList<Object> list = new ArrayList<>();
            while (true){
                list.add(ss);
                i++;
            }
        }catch (Exception e){
            System.out.println("循环次数"+ i + "---");
        }
        finally {

        }


    }


}

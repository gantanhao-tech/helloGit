package com.ylx.jvm;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author yanglx
 * @version 1.0
 * @date 2020-05-30
 */
public class OOM extends ClassLoader{

    /*
     OOM常见情况
            1.Stack Overflow  -> stackOverFlowError
            2.GC率过高 GC overhead limit exceeded
            3.直接内存溢出nio  Direct buffer memory
            4.线程数过多 Linux 默认1024个 unable to create new native thread
            5.HeapSpace
            6.Metaspace
     */
    public static void main(String[] args) {

        //-Xms60m -Xmx60m -XX:+PrintGCDetails
        OOM oom = new OOM();
        int i = 0;

        try {
            while (true){

                ++i;
                ClassWriter classWriter = new ClassWriter(0);
                //版本，修饰符，类名，包名，父类，实现的接口
                classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Class" + i , null, "java/lang/Object", null);
                //生成二进制数组
                byte[] bytes = classWriter.toByteArray();

                oom.defineClass("Class"+i, bytes, 0 ,bytes.length);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        finally{
            System.out.println("*************i:" + i);
        }


    }

    private static void createMorethread() {
        int i =0;
        try {
            while (true){
                new Thread(() -> {
                    try { TimeUnit.SECONDS.sleep(Integer.MAX_VALUE); } catch (InterruptedException e) { e.printStackTrace(); }
                },String.valueOf(++i)).start();
            }
        } finally {
            System.out.println("**********************i:" + i);
        }

    }

    private static void directBufferMemoryError() {
        //-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m

        ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }

    private static void overheadError() {
        //-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
        int i = 0;
        List<String> list = new ArrayList<>();
        try {
            while (true){

                list.add(String.valueOf(++i).intern());
            }
        }catch (Exception e){

            e.printStackTrace();
            throw e;
        }finally {
            System.out.println("***********i:" + i);
        }
    }

    private static void heapSpaceError() {
        String str = "yanglx";

        byte[] bytes = new byte[8*1024*1024];
    }


    private static void stackOverFlowError() {
        stackOverFlowError();
    }


}

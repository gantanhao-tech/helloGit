package com.ylx.jvm;

import java.util.concurrent.TimeUnit;

/**
 * @author yanglx
 * @version 1.0
 * @date 2020-05-30
 */
public class HelloGC {

    //-XX:+PrintGCDetails
    //-XX:MetaspaceSize=128m
    //-XX:MaxTenuringThreshold 年轻代最多活过多少次进入老年代，默认15
    //jps -flag 参数 进程号
    //jps -flags  进程号
    /*
    查看JVM默认值
        java -XX:+PrintFlagsInitial
        java -XX:+PrintFlagsFinal
        java -XX:+PrintCommandLineFlag
     */

    /*
     常用配置 -Xms10m -Xmx10m -Xss1024k -XX:MetaspaceSize=128m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseSerialGC

     默认
     -XX:InitialHeapSize=125482688
     -XX:MaxHeapSize=2007723008
     -XX:+PrintCommandLineFlags
     -XX:+UseCompressedClassPointers
     -XX:+UseCompressedOops
     -XX:-UseLargePagesIndividualAllocation
     -XX:+UseParallelGC
     ==============================
       -XX:InitialHeapSize=10485760
       -XX:MaxHeapSize=10485760
       -XX:MetaspaceSize=134217728
       -XX:+PrintCommandLineFlags
       -XX:+PrintGCDetails
       -XX:ThreadStackSize=1024
       -XX:+UseCompressedClassPointers
       -XX:+UseCompressedOops
       -XX:-UseLargePagesIndividualAllocation
       -XX:+UseSerialGC
     */

    /*
     垃圾收集算法 1.引用计数 2.复制 3.标记清除 4.标记整理
     垃圾收集器 1.GCSerial 2.GCParallel ParallelOldGC 3.CMS ConcMarkSweep
     */
    public static void main(String[] args) {
        System.out.println("*********helloGC");
//        long totalMemory = Runtime.getRuntime().totalMemory();
//        long maxMemory = Runtime.getRuntime().maxMemory();
//        System.out.println("maxMemory = " + maxMemory/1024/1024);
//        System.out.println("totalMemory = " + totalMemory/1024/1024);

//        try { TimeUnit.SECONDS.sleep(Integer.MAX_VALUE); } catch (InterruptedException e) { e.printStackTrace(); }
    }
}

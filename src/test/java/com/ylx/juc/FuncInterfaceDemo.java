package com.ylx.juc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author yanglx
 * @version 1.0
 * @date 2020-05-26
 */
public class FuncInterfaceDemo {


    public static void main(String[] args) {

    }

    private static void SupplierDemo() {
        Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return "你好。。六六";

            }
        };

        System.out.println("supplier = " + supplier.get());
    }

    private static void PredicateDemo() {
        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer s) {
                return s.compareTo(3)>0;
            }
        };

        Predicate<Integer> predicate2 = new Predicate<Integer>() {
            @Override
            public boolean test(Integer s) {
                return s.compareTo(5)<0;
            }
        };
        Predicate<Integer> and = predicate.and(predicate2);

        System.out.println("predicate2 = " + and.test(4));
        System.out.println("predicate = " + predicate.test(4));
    }

    private static void FunctionDemo() {
        Function<Integer, String> func = new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return "今年" + integer + "岁了";
            }
        };
        Function<Integer, String> func2 = new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return "今年" + integer + "岁了";
            }
        };


        System.out.println("func = " + func.apply(26));
        System.out.println("func = " + func.apply(16));
    }

    private static void consumerTest() {
        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer s) {
                System.out.println("come in consumer ..." + s);
            }
        };

        consumer.accept(1025);
        consumer.accept(10251);
        consumer.accept(10252);
    }
}
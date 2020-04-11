package info.luckydog.javacore.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * ParallelStreamDemo
 *
 * @author eric
 * @since 2019/12/14
 */
public class ParallelStreamDemo {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        List<Integer> numList = new ArrayList<>(10000);

        for (int i = 0; i < 10000; i++) {
            numList.add(i);
        }

        CopyOnWriteArraySet<Thread> processThreadSet = new CopyOnWriteArraySet<>();
        numList.parallelStream().forEach(num -> {
            processThreadSet.add(Thread.currentThread());
        });

        System.out.println("处理线程数量：" + processThreadSet.size());
        System.out.println("本机CPU线程数量：" + Runtime.getRuntime().availableProcessors());

    }
}

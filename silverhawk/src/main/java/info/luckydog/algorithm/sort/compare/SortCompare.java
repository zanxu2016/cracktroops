package info.luckydog.algorithm.sort.compare;

import info.luckydog.algorithm.sort.SortDecorator;
import info.luckydog.algorithm.sort.impl.*;
import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.TimeUnit;

/**
 * SortCompare
 *
 * @author eric
 * @since 2019/5/25
 */
public class SortCompare {

    public static double time(String alg, Double[] a) {
        StopWatch timer = StopWatch.createStarted();

        if (alg.equals("Insertion")) {
            new SortDecorator(new InsertionSort()).sort(a);
        }
        if (alg.equals("Selection")) {
            new SortDecorator(new SelectionSort()).sort(a);
        }
        if (alg.equals("Shell")) {
            new SortDecorator(new ShellSort()).sort(a);
        }
        if (alg.equals("Merge")) {
            new SortDecorator(new MergeSort()).sort(a);
        }
        if (alg.equals("Quick")) {
            new SortDecorator(new QuickSort()).sort(a);
        }
        if (alg.equals("Heap")) {
            new SortDecorator(new HeapSort()).sort(a);
        }
        timer.stop();

        return timer.getTime(TimeUnit.MICROSECONDS);
    }

    public static double timeRandomInput(String alg, int n, int t) {
        //使用算法将 t 个长度为 n 的数组排序
        double total = 0.0;
        Double[] a = new Double[n];
        for (int i = 0; i < t; i++) {
            //进行一次测试（生成一个数组并排序）
            for (int j = 0; j < n; j++) {
                a[j] = StdRandom.uniform();
            }
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = "Insertion";
        String alg2 = "Selection";
        int n = 1000;
        int t = 1000;
        double t1 = timeRandomInput(alg1, n, t);
        double t2 = timeRandomInput(alg2, n, t);
        System.out.printf("For %d random Doubles\n  %s is", n, alg1);
        System.out.printf(" %.1f times faster than %s\n", t2 / t1, alg2);
    }

}

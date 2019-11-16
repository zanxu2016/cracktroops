package info.luckydog.javacore.concurrent;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFutureDemo
 *
 * @author eric
 * @since 2019/9/4
 */
public class CompletableFutureDemo {

    @Test
    public void baseUse() {

        long start = System.currentTimeMillis();
        System.out.println("start: " + start);

        CompletableFuture<Integer> cf01 = CompletableFuture.supplyAsync(() -> add(1, 1));
        System.out.println("cf01 executed...");
        CompletableFuture<Integer> cf02 = CompletableFuture.supplyAsync(() -> add(1, 1));
        System.out.println("cf02 executed...");
        CompletableFuture<Integer> cf03 = CompletableFuture.supplyAsync(() -> add(1, 1));
        System.out.println("cf03 executed...");
        try {
            System.out.println("result: " + (cf01.get() + cf02.get() + cf03.get()));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("cost:" + (end - start) + " ms.");
    }

    @Test
    public void handleException() {

        CompletableFuture<String> cf = CompletableFuture.supplyAsync(CompletableFutureDemo::getCurrThreadName);
    }


    private static int add(int a, int b) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return a + b;
    }

    private static String getCurrThreadName() {
        return Thread.currentThread().getName();
    }
}

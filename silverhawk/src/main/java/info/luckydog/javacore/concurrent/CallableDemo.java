package info.luckydog.javacore.concurrent;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * CallableDemo
 *
 * @author eric
 * @since 2019/9/4
 */
public class CallableDemo {

    @Test
    public void baseUse() {
        Callable<Integer> callable = () -> {
            Thread.sleep(1000);
            System.out.println("callable.call()");
            return 1 + 1;
        };

        FutureTask ft = new FutureTask(callable);
        new Thread(ft).start();

        try {
            System.out.println("result:" + ft.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

package info.luckydog.javacore.concurrent;

import org.junit.Test;

import java.util.Map;
import java.util.stream.Stream;

/**
 * TheadDemo
 *
 * @author eric
 * @since 2019/11/5
 */
public class TheadDemo {

    private final Object o = new Object();

    public void testWait() {
        System.out.println(Thread.currentThread().getName() + " do testWait...");
        synchronized (this.o) {
            System.out.println(Thread.currentThread().getName() + " get lock...");
            try {
                System.out.println(Thread.currentThread().getName() + " do wait...");
                o.wait(5000L);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " get interrupted...");
                e.printStackTrace();
            }
        }
    }

    public void testNotify() {
        System.out.println(Thread.currentThread().getName() + " do testNotify...");
        synchronized (this.o) {
            System.out.println(Thread.currentThread().getName() + " get lock...");
            System.out.println(Thread.currentThread().getName() + " do notify...");
            o.notify();
        }
    }

    public void testNotifyAll() {
        System.out.println(Thread.currentThread().getName() + " do testNotifyAll...");
        synchronized (this.o) {
            System.out.println(Thread.currentThread().getName() + " get lock...");
            System.out.println(Thread.currentThread().getName() + " do notifyAll...");
            o.notifyAll();
        }
    }

    @Test
    public void waitAndNotify() {
        final TheadDemo demo = new TheadDemo();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " running...");
            demo.testWait();
            System.out.println(Thread.currentThread().getName() + " do sth in the end...");
        }).start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " running...");
            demo.testWait();
            System.out.println(Thread.currentThread().getName() + " do sth in the end...");
        }).start();

//        new Thread(() -> {
//            System.out.println(Thread.currentThread().getName() + " running...");
//            demo.testNotify();
//            System.out.println(Thread.currentThread().getName() + " do sth in the end...");
//        }).start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " running...");
            demo.testNotifyAll();
            System.out.println(Thread.currentThread().getName() + " do sth in the end...");
        }).start();


    }

    @Test
    public void test() {
        // 查看当前jvm所有线程的堆栈信息，jstack命令的大部分功能都能实现
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        for (Map.Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet()) {
            Thread thread = entry.getKey();
            StackTraceElement[] elements = entry.getValue();
            System.out.println("thread name: " + thread.getName());
            for (StackTraceElement element : elements) {
                System.out.println("stack trace element: " + element);
            }
        }
    }
}

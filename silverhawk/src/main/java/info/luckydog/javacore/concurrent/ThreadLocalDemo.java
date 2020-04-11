package info.luckydog.javacore.concurrent;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocalDemo
 *
 * @author eric
 * @since 2019/12/12
 */
public class ThreadLocalDemo {

    private static ThreadLocal<String> tl = new ThreadLocal<>();

    @Test
    public void test() {

        ThreadLocalDemo tld = new ThreadLocalDemo();

        // 开启子线程
        Thread one = new Thread(() -> {
            // 设置本地变量
            ThreadLocalDemo tldThread = new ThreadLocalDemo();
            tldThread.getAndSetLocal();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tldThread.remove();
            System.out.println("thread one over.");
        });
        one.start();
//        try {
//            one.join();
//        } catch (InterruptedException e) {
//            System.out.println(Thread.currentThread().getName() + " interrupted.");
//        }
//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        // main线程设置本地变量
        tld.getAndSetLocal();
        // 删除main线程的本地变量
        tld.remove();
        System.out.println("tread main over.");
    }

    private void getAndSetLocal() {
        System.out.println("start " + Thread.currentThread().getName() + " get tl String: " + tl.get());
        tl.set(Thread.currentThread().getName());
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " interrupted.");
        }
        System.out.println("end " + Thread.currentThread().getName() + " get tl String: " + tl.get());
    }

    private void remove() {
        System.out.println("tl remove...");
        tl.remove();
    }

    @Test
    public void removeBeforeSet() {
        this.remove();
    }

}

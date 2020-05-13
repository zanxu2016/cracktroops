package info.luckydog.javacore.concurrent;

import org.junit.Test;

import java.util.Date;

public class DeadLockDemo {

    public static final String obj1 = "obj1";
    public static final String obj2 = "obj2";

    @Test
    public void test() {

        LockA la = new LockA();
        new Thread(la).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LockB lb = new LockB();
        new Thread(lb).start();


        try {
            Thread.sleep(300_000);// 睡5分钟，jps拿到pid，jstack pid | more 查看堆栈信息，可以看到【Found one Java-level deadlock】字样
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class LockA implements Runnable {
    public void run() {
        try {
            System.out.println(new Date().toString() + " LockA 开始执行");
            while (true) {
                synchronized (DeadLockDemo.obj1) {
                    System.out.println(new Date().toString() + " LockA 锁住 obj1");
                    Thread.sleep(3000); // 此处等待是给B能锁住机会
                    synchronized (DeadLockDemo.obj2) {
                        System.out.println(new Date().toString() + " LockA 锁住 obj2");
                        Thread.sleep(60 * 1000); // 为测试，占用了就不放
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class LockB implements Runnable {
    public void run() {
        try {
            System.out.println(new Date().toString() + " LockB 开始执行");
            while (true) {
                synchronized (DeadLockDemo.obj2) {
                    System.out.println(new Date().toString() + " LockB 锁住 obj2");
                    Thread.sleep(3000); // 此处等待是给A能锁住机会
                    synchronized (DeadLockDemo.obj1) {
                        System.out.println(new Date().toString() + " LockB 锁住 obj1");
                        Thread.sleep(60 * 1000); // 为测试，占用了就不放
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

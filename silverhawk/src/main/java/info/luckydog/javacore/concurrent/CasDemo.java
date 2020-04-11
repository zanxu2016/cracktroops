package info.luckydog.javacore.concurrent;

import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;

public class CasDemo {

    private volatile int value;

    private final static Unsafe unsafe = Unsafe.getUnsafe();

    private final static long valueOffset;

    static {
        try {
            valueOffset = unsafe.objectFieldOffset(CasDemo.class.getDeclaredField("value"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public CasDemo(int value) {
        this.value = value;
    }

    public static void main(String[] args) {
        AtomicInteger ai = new AtomicInteger(2);
        // 两个线程只能有一个成功
        new Thread(() -> {
            boolean b = ai.compareAndSet(2, 3);
            System.out.println(Thread.currentThread().getName() + " done? " + b + ". now ai = " + ai.get());
        }, "t1").start();

        new Thread(() -> {
            boolean b = ai.compareAndSet(2, 4);
            System.out.println(Thread.currentThread().getName() + " done? " + b + ". now ai = " + ai.get());
        }, "t2").start();
    }

    // 比较并替换
    public int compareAndSet(int expect, int newValue) {
        return unsafe.getAndSetInt(this, valueOffset, newValue);
    }
}

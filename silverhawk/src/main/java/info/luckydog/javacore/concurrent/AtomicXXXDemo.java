package info.luckydog.javacore.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 通过cas机制，保证原子性
 * 但有ABA问题
 */
public class AtomicXXXDemo {

    private final AtomicInteger ai = new AtomicInteger(2);
    private final AtomicStampedReference<Integer> asr = new AtomicStampedReference<>(2, 0);

    public static void main(String[] args) {
        AtomicXXXDemo atomicXXXDemo = new AtomicXXXDemo();
        System.out.println("before aba(), ai value=" + atomicXXXDemo.ai.get());
        atomicXXXDemo.aba();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after aba(), ai value=" + atomicXXXDemo.ai.get());
        System.out.println("==========================");

        // AtomicStampedReference通过版本号，解决ABA问题
        atomicXXXDemo.abaSolver();
    }

    // 模拟ABA场景
    private void aba() {

        new Thread(() -> {
            // 先睡1秒，让t2线程执行A->B->A
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = ai.compareAndSet(2, 3);
            System.out.println(Thread.currentThread().getName() + " done? " + b + ". now ai = " + ai.get());
        }, "t1").start();

        new Thread(() -> {
            boolean b = ai.compareAndSet(2, 4);
            System.out.println(Thread.currentThread().getName() + " done? " + b + ". now ai = " + ai.get());
            boolean b1 = ai.compareAndSet(4, 2);
            System.out.println(Thread.currentThread().getName() + " done? " + b1 + ". now ai = " + ai.get());
        }, "t2").start();
    }

    // 解决ABA问题
    private void abaSolver() {

        new Thread(() -> {
            // 先睡1秒，让t2线程执行A->B->A
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = asr.compareAndSet(2, 3, 0, 1);
            System.out.println(Thread.currentThread().getName() + " done? " + b + ". now asr reference = " + asr.getReference() + "; asr stamp = " + asr.getStamp());
        }, "t1").start();

        new Thread(() -> {
            boolean b = asr.compareAndSet(2, 4, 0, 1);
            System.out.println(Thread.currentThread().getName() + " done? " + b + ". now asr reference = " + asr.getReference() + "; asr stamp = " + asr.getStamp());
            boolean b1 = asr.compareAndSet(4, 2, 1, 2);
            System.out.println(Thread.currentThread().getName() + " done? " + b + ". now asr reference = " + asr.getReference() + "; asr stamp = " + asr.getStamp());
        }, "t2").start();
    }
}

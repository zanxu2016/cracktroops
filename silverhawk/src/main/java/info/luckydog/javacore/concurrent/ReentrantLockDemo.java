package info.luckydog.javacore.concurrent;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLockDemo
 *
 * @author eric
 * @since 2019/12/14
 */
public class ReentrantLockDemo<T> {

    private ArrayList<T> list = new ArrayList<>();

    private ReentrantLock lock = new ReentrantLock();

    public T get(int index) {
        try {
            lock.lock();
            return list.get(index);
        } finally {
            lock.unlock();
        }
    }

    public boolean set(T item) {
        try {
            lock.lock();
            return list.add(item);
        } finally {
            lock.unlock();
        }
    }

    public boolean remove(T item) {
        try {
            lock.lock();
            return list.remove(item);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ReentrantLockDemo<Integer> demo = new ReentrantLockDemo<>();

        Thread a = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + " : " + i + " has been set? " + demo.set(i));
                } catch (Exception e) {
                    System.err.println("caught error when set item " + i);
                }
            }
        });
        a.start();
        a.join();

        Thread b = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + " : " + demo.get(i) + " has been gotten.");
                } catch (Exception e) {
                    System.err.println(Thread.currentThread().getName() + " : " + "caught error when get index " + i);
                }
            }
        });
        b.start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + " : " + i + " has been removed? " + demo.remove(i));
                } catch (Exception e) {
                    System.err.println("caught error when remove item " + i);
                }
            }
        }).start();


    }

}

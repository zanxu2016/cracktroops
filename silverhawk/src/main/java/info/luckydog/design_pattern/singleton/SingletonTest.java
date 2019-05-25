package info.luckydog.design_pattern.singleton;

import org.junit.Test;

public class SingletonTest {

    @Test
    public void test() {
        Class clazz = Class.getInstance();

        System.out.println(clazz);

        Class clazz2 = Class.getInstance();
        System.out.println(clazz2);
    }

    @Test
    public void testMultiThread() {
        Thread thread1 = new MyThread("thread1");
        Thread thread2 = new MyThread("thread2");
        Thread thread3 = new MyThread("thread3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class MyThread extends Thread {

    public MyThread() {
    }

    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(this.getName() + "--");
        Class clazz = Class.getInstance();
        System.out.println(clazz);
    }
}

package info.luckydog.javacore.concurrent;

import java.util.concurrent.TimeUnit;

public class SynchronizedDemo {

    private int num;

    public static void main(String[] args) {

        SynchronizedDemo demo = new SynchronizedDemo();

        Thread t1 = new Thread(() -> {
            System.out.println("t1...");
            synchronized (demo) {
                for (int i = 0; i < 20; i++) {
                    int num = demo.getNum();
                    num++;
                    demo.setNum(num);
                }
            }

        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            System.out.println("t2...");
            int num = demo.getNum();
            num++;
            synchronized (demo) {
                demo.setNum(num);
            }
        }, "t2");
        t2.start();

        sleep(3);
        System.out.println("demo.num = " + demo.getNum());
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        System.out.println("setNum Thread: " + Thread.currentThread().getName());
        this.num = num;
    }

    public static void sleep(long timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

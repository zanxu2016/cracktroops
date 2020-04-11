package info.luckydog.javacore.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * volatile
 * 只能保证
 * 1、内存可见性（拿到的变量是主内存中最新值）
 * 2、禁止指令重排（禁止编译器对指令进行优化重排）
 * 保证不了
 * 1、原子性（对变量修改的原子性）
 */
public class VolatileDemo {

    public static void main(String[] args) {
        VolatileDemo demo = new VolatileDemo();
//        demo.demo1();
        demo.demo2();
    }

    // 多线程变量累加，保证不了原子性（read and load / use and assign / write and store）
    private void demo2() {
        Demo2 demo2 = new Demo2();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                demo2.a++;
            }, "thread-" + i).start();
        }
        System.out.println("demo2.a=" + demo2.a);
    }

    // 变量控制流程，工作内存和主内存不一致
    private void demo1() {
        Demo1 demo1 = new Demo1();
        demo1.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        demo1.flag = false;// 虽然flag改为false，但Demo1线程并未停止
        System.out.println("demo1.a = " + demo1.b);
    }

    class Demo1 extends Thread {
        private int b;
        private /*volatile*/ boolean flag = true;

        @Override
        public void run() {
            while (flag) {
                b++;
            }
        }
    }

    class Demo2 {
        private /*volatile*/ int a;
    }
}

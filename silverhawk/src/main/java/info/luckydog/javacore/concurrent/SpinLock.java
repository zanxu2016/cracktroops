package info.luckydog.javacore.concurrent;

import org.junit.Test;

/**
 * SpinLock
 *
 * @author eric
 * @since 2019/12/11
 */
public class SpinLock {

    private int num = 0;

    public void testFor() {
        // for循环自旋，依然会被其他线程抢占
        for (; ; ) {
            System.out.println(Thread.currentThread().getName() + " in spin lock start: " + this.num);
            if (this.num == 10) {
                System.out.println(Thread.currentThread().getName() + " get num \n");
                break;
            }
            this.num++;
            System.out.println(Thread.currentThread().getName() + " in spin lock end: " + this.num + "\n");
        }
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Test
    public void test() {
        SpinLock spinLock = new SpinLock();
        new Thread(spinLock::testFor).start();
        new Thread(spinLock::testFor).start();
        new Thread(spinLock::testFor).start();
        new Thread(spinLock::testFor).start();
        new Thread(spinLock::testFor).start();
        new Thread(spinLock::testFor).start();
        new Thread(spinLock::testFor).start();
        new Thread(spinLock::testFor).start();
        new Thread(spinLock::testFor).start();

    }
}

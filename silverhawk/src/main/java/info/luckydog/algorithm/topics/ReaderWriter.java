package info.luckydog.algorithm.topics;

import java.util.concurrent.Semaphore;

/**
 * ReaderWriter
 *
 * @author eric
 * @since 2019/11/16
 */
public class ReaderWriter {

    public static void main(String[] args) {
        new Thread(new Reader()).start();
        new Thread(new Reader()).start();
        new Thread(new Writer()).start();
    }
}


class Sign {
    static Semaphore db = new Semaphore(1); // 信号量：控制对数据库的访问
    static Semaphore mutex = new Semaphore(1); // 信号量：控制对临界区的访问
    static int rc = 0; // 记录正在读或者想要读的进程数
}

class Reader implements Runnable {

    public void run() {
        try {
            //互斥对rc的操作
            Sign.mutex.acquire();
            Sign.rc++;  //又多了一个读线程
            if (Sign.rc == 1) Sign.db.acquire(); //如果是第一个读进程开始读取DB，则请求一个许可，使得写进程无法操作 DB
            Sign.mutex.release();

            //无临界区控制，多个读线程都可以操作DB
            System.out.println("[R]   " + Thread.currentThread().getName() + ": read data....");
            Thread.sleep(100);

            //互斥对rc的操作
            Sign.mutex.acquire();
            Sign.rc--;
            if (Sign.rc == 0) Sign.db.release(); //如果最后一个读进程读完了，则释放许可，让写进程有机会操作DB
            Sign.mutex.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Writer implements Runnable {

    public void run() {
        try {
            // 与读操作互斥访问DB
            Sign.db.acquire();
            System.out.println("[W]   " + Thread.currentThread().getName()
                    + ": write data....");
            Thread.sleep(100);
            Sign.db.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

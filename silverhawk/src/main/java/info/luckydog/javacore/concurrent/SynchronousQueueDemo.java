package info.luckydog.javacore.concurrent;

import org.junit.Test;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 同步队列
 */
public class SynchronousQueueDemo {
    /**
     * 生产者
     */
    static class SynchronousQueueProducer implements Runnable {

        protected BlockingQueue<String> blockingQueue;
        final Random random = new Random();

        public SynchronousQueueProducer(BlockingQueue<String> queue) {
            this.blockingQueue = queue;
        }

        @Override
        public void run() {
            // 隔一秒生产一个UUID
            while (true) {
                try {
                    String data = UUID.randomUUID().toString();
                    System.out.println("Put: " + data);
                    blockingQueue.put(data);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 消费者
     */
    static class SynchronousQueueConsumer implements Runnable {

        protected BlockingQueue<String> blockingQueue;

        public SynchronousQueueConsumer(BlockingQueue<String> queue) {
            this.blockingQueue = queue;
        }

        @Override
        public void run() {
            // 隔两秒消费一个UUID
            while (true) {
                try {
                    String data = blockingQueue.take();
                    System.out.println(Thread.currentThread().getName() + " take(): " + data);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
        // 模拟场景
        // 一个生产者隔一秒生产一个UUID
        // 两个消费者分别隔两秒去消费一个UUID（交替消费）
        final BlockingQueue<String> synchronousQueue = new SynchronousQueue<>();

        SynchronousQueueProducer queueProducer = new SynchronousQueueProducer(synchronousQueue);
        new Thread(queueProducer).start();

        SynchronousQueueConsumer queueConsumer1 = new SynchronousQueueConsumer(synchronousQueue);
        new Thread(queueConsumer1).start();

        SynchronousQueueConsumer queueConsumer2 = new SynchronousQueueConsumer(synchronousQueue);
        new Thread(queueConsumer2).start();

    }

    @Test
    public void test() {
        String url = "https://static.yuxiar.com/file/real/boring/name.jpg";
        System.out.println(getFileNameWithoutExtension(url));

        System.out.println("a".equals(null));
    }

    public static String getFileNameWithoutExtension(String url) {
        // 带扩展名的文件名
        String fileName = url.substring(url.lastIndexOf('/') + 1, url.length());
        System.out.println(fileName);
        // 不带扩展名的文件名
        String fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));

        return fileNameWithoutExtn;
    }
}

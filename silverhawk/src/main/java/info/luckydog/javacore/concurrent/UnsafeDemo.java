package info.luckydog.javacore.concurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * UnsafeDemo
 *
 * 使用Unsafe，实现高并发生成唯一id
 *
 * @author eric
 * @since 2019/12/18
 */
public class UnsafeDemo {

    private static final Unsafe unsafe;

    private volatile long id = -1L;

    private static final long idOffset;

    static {
        try {
            Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
            Field field = unsafeClass.getDeclaredField("theUnsafe");
            field.setAccessible(true);// 取消安全检查，提升反射效率
            unsafe = (Unsafe) field.get(null);

            idOffset = unsafe.objectFieldOffset(UnsafeDemo.class.getDeclaredField("id"));

        } catch (Exception e) {
            System.err.println(e.getMessage());
            // 必须要将异常抛出，不然 Unsafe 编译不通过，认为未初始化
            throw new Error(e);
        }
    }

    // 生成下一个id
    public long nextId() {
        // 自旋，获取下一秒，并通过cas设置id
        for (; ; ) {
            // 当前id
            long id = this.getId();
            // 当前时间戳
            long timestamp = System.currentTimeMillis();
            //
            if (timestamp < id) {
                throw new RuntimeException("Clock moved backwards. Refusing to generate id for " + (id - timestamp) + " milliseconds.");
            }
            if (id == timestamp) {
                timestamp = tilNextMillis(id);
            }

            if (unsafe.compareAndSwapLong(this, idOffset, id, timestamp)) {
                System.out.println(Thread.currentThread().getName() + " cas success");
                return this.getId();
            }
        }
    }

    // 等待下一毫秒
    private static long tilNextMillis(long lastTimestamp) {
        System.out.println(Thread.currentThread().getName() + " get next millisecond...");
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    public long getId() {
        return id;
    }

    public static void main(String[] args) {

        UnsafeDemo demo = new UnsafeDemo();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " generate next id: " + demo.nextId());
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " generate next id: " + demo.nextId());
            }
        }).start();
    }
}

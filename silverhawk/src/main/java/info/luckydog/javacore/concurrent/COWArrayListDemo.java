package info.luckydog.javacore.concurrent;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * COWArrayList
 *
 * @author eric
 * @since 2019/12/13
 */
public class COWArrayListDemo {

    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> cowArrayList = new CopyOnWriteArrayList<>();

        // 添加测试数据
        for (int i = 0; i < 200; i++) {
            cowArrayList.add(i);
        }

        // 先获取集合的迭代器
        Iterator iterator = cowArrayList.iterator();

        // 创建线程A，删除元素
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": remove start");
            cowArrayList.remove(199);
            cowArrayList.remove(198);
            System.out.println(Thread.currentThread().getName() + ": remove end");
        }).start();

        // 创建线程B，读取迭代器中的元素
        new Thread(() -> {
            while (iterator.hasNext()) {
                System.out.println(Thread.currentThread().getName() + ": " + iterator.next());
            }
        }).start();

        /*
        测试结果：
        虽然线程A删除了某些元素，线程B依然能读取到被删除的元素
        说明CopyOnWriteArrayList迭代器的弱一致性。
         */

    }

}

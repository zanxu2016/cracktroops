package info.luckydog.design_pattern.observer;

import org.junit.Test;

/**
 * 观察者模式测试类
 */
public class ObserverTest {

    @Test
    public void newsObserverTest() {
        // 创建新闻生产者
        NewsProvider provider = new NewsProvider();

        User user;
        for (int i = 0; i < 10; i++) {
            user = new User("user-" + i);
            try {
                long randomSleepMillis = (long) (Math.random() * 2000);
                Thread.sleep(randomSleepMillis);
                System.out.println("user-" + i + " sleep for " + randomSleepMillis + " ms");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            provider.register(user);
        }
    }

    @Test
    public void jdkObserverTest() {
        JDKObservable jdkObservable = new JDKObservable();

        JDKObserver jdkObserver;
        for (int i = 0; i < 10; i++) {
            jdkObserver = new JDKObserver("jdkObserver-" + i);
            try {
                long randomSleepMillis = (long) (Math.random() * 2000);
                Thread.sleep(randomSleepMillis);
                System.out.println("jdkObserver-" + i + " sleep for " + randomSleepMillis + " ms");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            jdkObservable.addObserver(jdkObserver);
        }
    }
}

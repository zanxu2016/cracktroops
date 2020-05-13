package info.luckydog.design_pattern.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 新闻生产者
 */
public class NewsProvider implements MyObservable {

    private static final long DELAY = 2 * 1000;

    private List<MyObserver> myObservers;

    public NewsProvider() {
        this.myObservers = new ArrayList<>();
        this.generateNews();
    }

    // 生成新闻，并模拟发送新闻
    private void generateNews() {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int titleCount = 1;
            int contentCount = 1;

            @Override
            public void run() {
                send(new NewsModel("title:" + titleCount++, "content:" + contentCount++));
            }
        }, DELAY, 1000);
    }

    @Override
    public void register(MyObserver observer) {
        if (observer == null) return;
        synchronized (this) {
            if (!myObservers.contains(observer)) {
                myObservers.add(observer);
                System.out.println("有一个新注册用户！用户量为：" + myObservers.size());
            }
        }
    }

    @Override
    public void send(NewsModel newsModel) {
        for (MyObserver myObserver : myObservers) {
            myObserver.receive(newsModel);
        }
    }

    @Override
    public synchronized void remove(MyObserver observer) {
        myObservers.remove(observer);
    }
}

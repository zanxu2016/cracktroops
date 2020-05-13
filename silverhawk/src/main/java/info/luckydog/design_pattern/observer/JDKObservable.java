package info.luckydog.design_pattern.observer;

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

public class JDKObservable extends Observable {

    private static final long DELAY = 2 * 1000;

    public JDKObservable() {
        this.generateNews();
    }

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

    public void send(NewsModel newsModel) {
        System.out.println("send news: {title = " + newsModel.getTitle() + ", content = " + newsModel.getContent() + "}.");
        super.setChanged();
        super.notifyObservers(newsModel);
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
        System.out.println("新注册用户，现有用户量：" + super.countObservers());
    }
}

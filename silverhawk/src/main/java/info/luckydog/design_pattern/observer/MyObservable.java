package info.luckydog.design_pattern.observer;

/**
 * 被观察者接口-生产者
 * 注册、发送、注销
 */
public interface MyObservable {

    void register(MyObserver observer);

    void remove(MyObserver observer);

    void send(NewsModel newsModel);
}

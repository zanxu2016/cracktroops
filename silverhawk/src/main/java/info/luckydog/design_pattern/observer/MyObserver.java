package info.luckydog.design_pattern.observer;

/**
 * 观察者接口-消费者
 * 接收
 */
public interface MyObserver {

    void receive(NewsModel newsModel);
}

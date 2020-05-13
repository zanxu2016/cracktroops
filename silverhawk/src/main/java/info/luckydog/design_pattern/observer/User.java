package info.luckydog.design_pattern.observer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 新闻订阅者-消费者
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements MyObserver {

    private String mName;

    @Override
    public void receive(NewsModel newsModel) {
        System.out.println(this.mName + " receive news: " + newsModel.getTitle() + " " + newsModel.getContent());
    }
}

package info.luckydog.design_pattern.observer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Observable;
import java.util.Observer;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JDKObserver implements Observer {

    private String name;

    @Override
    public void update(Observable o, Object arg) {
        NewsModel news = (NewsModel) arg;
        System.out.println(this.name + " received news: {title = " + news.getTitle() + ", content = " + news.getContent() + "}.");
    }
}

package info.luckydog.design_pattern.observer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 新闻类
 * 信息载体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsModel {

    private String title;
    private String content;
}

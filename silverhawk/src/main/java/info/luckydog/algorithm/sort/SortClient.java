package info.luckydog.algorithm.sort;

import info.luckydog.algorithm.sort.impl.InsertionSort;
import info.luckydog.algorithm.sort.impl.SelectionSort;
import org.junit.Test;

/**
 * 排序算法测试类
 *
 * @author eric
 * @since 2019/5/25
 */
public class SortClient {

    private SortDecorator sortDecorator = null;

    private Integer[] a = {2, 3, 5, 1, 6, 9, 7, 8, 4};

    @Test
    public void testSelectSort() {
        sortDecorator = new SortDecorator(new SelectionSort());
        sortDecorator.sort(a);
    }

    @Test
    public void testInsertSort() {
        sortDecorator = new SortDecorator(new InsertionSort());
        sortDecorator.sort(a);
    }


}

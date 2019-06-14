package info.luckydog.algorithm.sort;

import info.luckydog.algorithm.sort.compare.StdDraw;
import info.luckydog.algorithm.sort.impl.InsertionSort;
import info.luckydog.algorithm.sort.impl.MergeSort;
import info.luckydog.algorithm.sort.impl.SelectionSort;
import info.luckydog.algorithm.sort.impl.ShellSort;
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
    private Double[] b = {2d, 3d, 5d, 1d, 6d, 9d, 7d, 8d, 4d};

    @Test
    public void testSelectionSort() {
        sortDecorator = new SortDecorator(new SelectionSort());
        sortDecorator.sort(a);
    }

    @Test
    public void testSelectionSortAnimation() {
        sortDecorator = new SortDecorator(new SelectionSort());
        sortDecorator.sort(b);
        StdDraw.pause(5000);
    }

    @Test
    public void testInsertionSort() {
        sortDecorator = new SortDecorator(new InsertionSort());
        sortDecorator.sort(a);
    }

    @Test
    public void testInsertionSortAnimation() {
        sortDecorator = new SortDecorator(new InsertionSort());
        sortDecorator.sort(b);
        StdDraw.pause(5000);
    }

    @Test
    public void testShellSort() {
        sortDecorator = new SortDecorator(new ShellSort());
        sortDecorator.sort(a);
    }

    @Test
    public void testShellSortAnimation() {
        sortDecorator = new SortDecorator(new ShellSort());
        sortDecorator.sort(b);
        StdDraw.pause(5000);
    }

    @Test
    public void testMergeSort() {
        sortDecorator = new SortDecorator(new MergeSort());
        sortDecorator.sort(a);
    }

    @Test
    public void testMergeSortAnimation() {
        sortDecorator = new SortDecorator(new MergeSort());
        sortDecorator.sort(b);
        StdDraw.pause(5000);
    }


}

package info.luckydog.algorithm.sort;

/**
 * 排序算法接口
 *
 * @author eric
 * @since 2019/05/24
 */
public interface Sort {

    void sort(Comparable[] a);

    boolean less(Comparable v, Comparable w);

    void exchange(Comparable[] a, int i, int j);

    void show(Comparable[] a);

    boolean isSorted(Comparable[] a);
}

package info.luckydog.algorithm.sort;

/**
 * 排序算法抽象类
 * 实现比较、交换、展示、排序结果等方法
 * <p>
 * 核心方法-排序方法交给具体排序算法实现
 *
 * @author eric
 * @since 2019/05/25
 */
public abstract class AbstractSort implements Sort {

    private int exchCount;//交换次数
    private int compareCount;//比较次数

    public void sort(Comparable[] a) {
    }

    public boolean less(Comparable v, Comparable w) {
        compareCount++;
        return v.compareTo(w) < 0;
    }

    public void exchange(Comparable[] a, int i, int j) {
        exchCount++;
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public int getExchangeCount() {
        return exchCount;
    }

    public void setExchCount(int exchCount) {
        this.exchCount = exchCount;
    }

    public int getCompareCount() {
        return compareCount;
    }

    public void setCompareCount(int compareCount) {
        this.compareCount = compareCount;
    }
}

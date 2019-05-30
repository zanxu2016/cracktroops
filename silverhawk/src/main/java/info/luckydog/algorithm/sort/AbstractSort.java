package info.luckydog.algorithm.sort;

import info.luckydog.algorithm.sort.compare.StdDraw;

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
        //各种排序算法自行实现
    }

    public boolean less(Comparable v, Comparable w) {
        compareCount++;
        System.out.println("第 " + compareCount + " 次比较 -> " + v + " 和 " + w);
        return v.compareTo(w) < 0;
    }

    public void exchange(Comparable[] a, int i, int j) {
        exchCount++;
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
        System.out.print("更换 " + a[i] + " 和 " + a[j] + " -> ");
        show(a);
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

    //显示动画
    public void showAnimation(Comparable[] a) {
        StdDraw.setXscale(0.0, a.length);
        StdDraw.setYscale(0.0, a.length);
        StdDraw.setPenRadius(0.005);
        StdDraw.pause(100);
        StdDraw.clear(StdDraw.GRAY);
        StdDraw.setPenColor(StdDraw.BLACK);
        if (isSorted(a)) {
            StdDraw.setPenColor(StdDraw.RED);
        }
        for (int i = 0; i < a.length; i++) {
            StdDraw.line(i * 1.0, 0.0, i * 1.0, Double.valueOf(String.valueOf(a[i])) * 1.0);
        }
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

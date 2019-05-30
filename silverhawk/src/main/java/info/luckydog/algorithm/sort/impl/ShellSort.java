package info.luckydog.algorithm.sort.impl;

import info.luckydog.algorithm.sort.AbstractSort;
import info.luckydog.algorithm.sort.compare.StdDraw;

/**
 * 希尔排序
 * 使数组中任意间隔为 h 的元素都是有序的。
 * 这样的数组叫 h 有序数组。
 * 一个 h 有序数组就是 h 个互相独立的有序数组编织在一起组成的一个数组。
 * 若 h 很大，则能将元素移动到很远的地方，为实现更小的 h 有序创造方便。
 * 用这种方式，对于任意以 1 结尾的 h 序列，都能将数组排序。
 * <p>
 * 实现方法：
 * 对于每个 h，用插入排序将 h 个子数组独立地排序。但因为子数组是相互独立的，
 * 一个更简单的方法是在 h 子数组中将每个元素交换到比它大的元素之前去（将比它大的元素向右移动一格）。
 * 只需要在插入排序的代码中将移动元素的距离由 1 改为 h 即可。
 * 这样，希尔排序的实现就转化为了一个类似插入排序但使用不同增量的过程。
 * <p>
 * 高效的原因：
 * 权衡了子数组的规模和有序性。排序前各个子数组都很短，排序后子数组都是部分有序的，这两种情况都很适合插入排序。
 * 子数组部分有序的程度取决于递增序列的选择。
 *
 * @author eric
 * @since 2019/05/25
 */
public class ShellSort extends AbstractSort {

    @Override
    public void sort(Comparable[] a) {
        //将 a[] 按升序排列
        int n = a.length;
        int h = 1;
        while (h < n / 3) {
            System.out.println("h 为 " + h);
            h = 3 * h + 1;
            System.out.println("把 h 改为 " + h);
        }
        while (h >= 1) {
            //将数组变为 h 有序
            for (int i = h; i < n; i++) {
                System.out.println("i = " + i);
                //将 a[i] 插入到 a[i-h], a[i-2*h], a[i-3*h]... 之中
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exchange(a, j, j - h);
                }

                showAnimation(a);
                StdDraw.pause(1000);
            }
            System.out.println("while 循环时 h 为 " + h);
            h = h / 3;
            System.out.println("h 缩减为 " + h);
            System.out.println("=================================");
        }
    }
}

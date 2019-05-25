package info.luckydog.algorithm.sort.impl;

import info.luckydog.algorithm.sort.AbstractSort;

/**
 * 插入排序
 * <p>
 * 1、将每个元素插入到其他已经有序的数组的位置。
 * 2、为了给要插入的元素腾出空间，需要将其余所有元素在插入前都向右移一位。
 * 3、当前索引左边的所有元素都是有序的，但它们的最终位置还不确定，为了给更小元素腾出空间，它们可能都会被移动。
 * 4、当索引到达数组右端时，排序完成。
 * 5、插入排序所需时间取决于输入中元素的初始顺序。
 * <p>
 * 对于随机排列的长度为 N 且主键不重复的数组，平均情况下插入排序需要大约 N 平方的四分之一次比较，以及大约 N 平方的四分之一交换。
 * 最坏情况下需要大约 N 平方的二分之一次比较和大约 N 平方的二分之一次交换。
 * 插入排序需要的交换操作和数组中倒置的数量相同，需要的比较次数大于等于倒置的数量，小于等于倒置的数量加上数组的大小再减一。
 *
 * 倒置：数组中的两个顺序颠倒的元素。
 * 部分有序数组：数组中倒置的数量小于数组大小的某个倍数。
 *
 * 应用场景：（部分有序数组）
 * 1、数组中每个元素距离它最终位置都不远；
 * 2、一个有序的大数组接一个小数组；
 * 3、数组中只有几个元素的位置不正确；
 * 4、小规模数组。
 *
 * @author eric
 * @since 2019/05/24
 */
public class InsertSort extends AbstractSort {

    public void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = {2, 3, 5, 1, 6, 9, 7, 8, 4};
        InsertSort sort = new InsertSort();
        sort.sort(a);
    }
}

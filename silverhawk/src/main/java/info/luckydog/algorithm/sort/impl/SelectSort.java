package info.luckydog.algorithm.sort.impl;

import info.luckydog.algorithm.sort.AbstractSort;

/**
 * 选择排序
 * <p>
 * 首先，找到数组中最小元素；
 * 其次，将它和数组中的第一个元素交换位置（若第一个元素就是最小元素，则与自己交换）；
 * 再次，在剩下的元素中找到最小元素，将它与数组的第二个元素交换位置；
 * 以此类推，直到将整个数组排序。
 * <p>
 * 长度为 N 的数组，选择排序需要大约 N 平方的一半 和 N 次交换。
 * <p>
 * 特点：1、运行时间和输入无关。2、数据移动最少。
 *
 * @author eric
 * @since 2019/05/24
 */
public class SelectSort extends AbstractSort {

    @Override
    public void sort(Comparable[] a) {
        // 将 a[] 按升序排列
        int n = a.length;// 数组长度
        for (int i = 0; i < n; i++) {
            // 将 a[i] 和 a[i+1..n] 中最小的元素交换
            int min = i;// 最小元素的索引
            for (int j = i + 1; j < n; j++) {
                if (less(a[j], a[min])) {
                    min = j;// 找最小元素索引
                }
            }
            exch(a, i, min);// 交换
        }
    }

    public static void main(String[] args) {
        Integer[] a = {2, 3, 5, 1, 6, 9, 7, 8, 4};
        SelectSort sort = new SelectSort();
        sort.sort(a);
        System.out.println(sort.getCompareCount());
        System.out.println(sort.getExchCount());
    }
}

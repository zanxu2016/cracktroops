package info.luckydog.algorithm.sort.impl;

import info.luckydog.algorithm.sort.AbstractSort;

/**
 * 归并排序
 *
 * @author eric
 * @since 2019/05/31
 */
public class MergeSort extends AbstractSort {

    @Override
    public void sort(Comparable[] a) {
        Comparable[] tempArr = new Comparable[a.length];
        sort(a, tempArr, 0, a.length - 1);
    }

    /**
     * 归并排序
     *
     * @param arr        排序数组
     * @param tempArr    临时存储数组
     * @param startIndex 排序起始位置
     * @param endIndex   排序终止位置
     */
    private void sort(Comparable[] arr, Comparable[] tempArr, int startIndex, int endIndex) {
        if (endIndex <= startIndex) {
            return;
        }
        //中部下标
        int middleIndex = startIndex + (endIndex - startIndex) / 2;

        //分解
        sort(arr, tempArr, startIndex, middleIndex);
        sort(arr, tempArr, middleIndex + 1, endIndex);

        //归并
        merge(arr, tempArr, startIndex, middleIndex, endIndex);
    }

    /**
     * 归并
     *
     * @param arr         排序数组
     * @param tempArr     临时存储数组
     * @param startIndex  归并起始位置
     * @param middleIndex 归并中间位置
     * @param endIndex    归并终止位置
     */
    private void merge(Comparable[] arr, Comparable[] tempArr, int startIndex, int middleIndex, int endIndex) {
        //复制要合并的数据
        for (int s = startIndex; s <= endIndex; s++) {
            tempArr[s] = arr[s];
        }

        int left = startIndex;//左边首位下标
        int right = middleIndex + 1;//右边首位下标
        for (int k = startIndex; k <= endIndex; k++) {
            if (left > middleIndex) {
                //如果左边的首位下标大于中部下标,证明左边的数据已经排完了。
                arr[k] = tempArr[right++];
            } else if (right > endIndex) {
                //如果右边的首位下标大于了数组长度,证明右边的数据已经排完了。
                arr[k] = tempArr[left++];
            } else if (less(tempArr[right], tempArr[left])) {
                arr[k] = tempArr[right++];//将右边的首位排入,然后右边的下标指针+1。
            } else {
                arr[k] = tempArr[left++];//将左边的首位排入,然后左边的下标指针+1。
            }
        }
    }
}

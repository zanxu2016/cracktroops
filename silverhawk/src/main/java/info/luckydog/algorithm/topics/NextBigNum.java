package info.luckydog.algorithm.topics;

import org.junit.Test;

import java.util.Arrays;

/**
 * NextBigNum
 * 找出给定整数的各位数字全排列中仅比原数大的整数
 * 分三步：
 * 1、从后往前，找到逆序区域边界下标
 * 2、将逆序区域中刚好大于边界前一位的数，与边界前一位互换
 * 3、将逆序区域数字改为顺序
 *
 * @author eric
 * @since 2019/7/2
 */
public class NextBigNum {

    @Test
    public void nextBigNumTest() {
        // 初始化数字数组
        int[] numArr = {3, 5, 9, 6, 8, 7};

        System.out.println("输入数字：" + getNum(numArr));

        // 获取下一个最大数
        System.out.println("下一个最大数为：" + getNextBigNum(numArr));
    }

    public Integer getNextBigNum(int[] numArr) {

        // 从后向前，定位逆序区域的前一位
        int index = getReverseIndex(numArr);
        System.out.println("逆序区域下标：" + index + "；对应元素：" + numArr[index]);
        if (index == 0) {
            return null;
        }

        int[] numArrCopy = Arrays.copyOf(numArr, numArr.length);

        // 将逆序区域前一位n与逆序区域中刚好比n大的数，互换位置
        exchangeNum(numArrCopy, index);
        System.out.println("互换位置后：" + getNum(numArrCopy));

        // 将逆序区域改为顺序
        reverse(numArrCopy, index);
        System.out.println("逆序改顺序后：" + getNum(numArrCopy));

        return getNum(numArrCopy);
    }

    private int getReverseIndex(int[] numArr) {

        for (int i = numArr.length - 1; i > 1; i--) {
            if (numArr[i] > numArr[i - 1]) {
                return i;
            }
        }

        return 0;
    }

    private void exchangeNum(int[] numArr, int index) {

        for (int i = numArr.length - 1; i >= index; i--) {
            if (numArr[i] > numArr[index - 1]) {
                int temp = numArr[index - 1];
                numArr[index - 1] = numArr[i];
                numArr[i] = temp;
                break;
            }
        }
    }

    private void reverse(int[] numArr, int index) {

        for (int i = index, j = numArr.length - 1; i < j; i++, j--) {
            int temp = numArr[i];
            numArr[i] = numArr[j];
            numArr[j] = temp;
        }
    }

    private Integer getNum(int[] numArr) {
        StringBuilder numStr = new StringBuilder();
        for (int i = 0; i < numArr.length; i++) {
            numStr.append(numArr[i]);
        }

        return Integer.valueOf(numStr.toString());
    }


}

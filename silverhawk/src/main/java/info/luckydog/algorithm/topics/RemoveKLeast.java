package info.luckydog.algorithm.topics;

import org.junit.Test;

/**
 * RemoveKLeast
 * 一个n位正整数，删除k位（k<=n）后，得到最小数
 * 1、遍历正整数字符串，将每一位字符放入一个新的字符串数组中
 * 2、放入数组之前，先比较本字符是否比上一位字符小，若是，则将上一位字符替换成本字符。
 * 3、生成新的字符数组，从左向右，除去左边的零。
 * 4、得到最小数
 * <p>
 * 贪心算法思想
 * 可以先从k=1寻找思路
 *
 * @author eric
 * @since 2019/7/3
 */
public class RemoveKLeast {

    @Test
    public void removeKLeastTest() {
//        String numStr = "134685792";
//        String numStr = "123456789";
//        String numStr = "100023456";
//        String numStr = "987654321";
//        String numStr = "12";
//        String numStr = "";
//        String numStr = null;
//        String numStr = "100";
        String numStr = "10200";
        int k = 1;
        System.out.println("numStr: " + numStr + "\nk: " + k);
        System.out.println("removeKLeast: " + removeKLeast(numStr, k));
    }

    private String removeKLeast(String num, int k) {

        if (num == null || num.length() == 0) {
            return null;
        }

        if (k == 0) {
            return num;
        }

        if (k >= num.length()) {
            return "0";
        }

        int numLength = num.length();

        int newNumLength = numLength - k;

        char[] newNumArr = new char[numLength];// 存放字符串各字符的数组

        int i = 0; // 字符串的指针
        int top = 0;// 新字符数组的指针
        for (; i < numLength; i++) {

            while (top > 0 && num.charAt(i) < newNumArr[top - 1] && k > 0) {// 比较字符串字符是否大于新的字符数组元素
                top--;
                k--;
            }
            newNumArr[top] = num.charAt(i);
            top++;
        }

        // 找到第一个非零字符下标
        int notZeroIndex = 0;

        for (; notZeroIndex < newNumLength; notZeroIndex++) {
            if (newNumArr[notZeroIndex] != '0') {
                break;
            }
        }

        return notZeroIndex >= newNumLength ? "0" : new String(newNumArr, notZeroIndex, newNumLength - notZeroIndex);
    }

}

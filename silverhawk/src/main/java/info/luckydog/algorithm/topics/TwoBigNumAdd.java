package info.luckydog.algorithm.topics;

import org.junit.Test;

/**
 * TwoBigNumAdd
 * 两个大数相加
 *
 * @author eric
 * @since 2019/7/4
 */
public class TwoBigNumAdd {

    @Test
    public void twoBigNumAddTest() {
        String numA = "1234567890";
        String numB = "987654321";
        System.out.println("numA: " + numA + ", numB: " + numB);

        String sum = addBigNum(numA, numB);
        System.out.println("sum: " + sum);
    }

    private String addBigNum(String numA, String numB) {

        int aLength = numA.length();
        int bLength = numB.length();
        int max = aLength > bLength ? aLength : bLength;
        int[] arrSum = new int[max + 1];

        for (int i = 0; i < max; i++) {
            int numASub = i < aLength ? Integer.valueOf(String.valueOf(numA.charAt(aLength - 1 - i))) : 0;
            int numBSub = i < bLength ? Integer.valueOf(String.valueOf(numB.charAt(bLength - 1 - i))) : 0;
            int tempSum = numASub + numBSub;
            if (tempSum >= 10) {
                tempSum -= 10;
                arrSum[i + 1] = 1;
            }
            arrSum[i] += tempSum;
        }

        int sumLength = arrSum.length;
        StringBuffer sb = new StringBuffer();
        for (int i = sumLength - 1; i >= 0; i--) {
            sb.append(arrSum[i]);
        }

        return sb.toString();
    }
}

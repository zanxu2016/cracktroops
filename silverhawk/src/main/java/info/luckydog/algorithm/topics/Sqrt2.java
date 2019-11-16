package info.luckydog.algorithm.topics;

import org.junit.Test;

/**
 * Sqrt2
 *
 * @author eric
 * @since 2019/10/11
 */
public class Sqrt2 {

    @Test
    public void getSqrt() {
        double x = 2;
        int n = 10;

        System.out.println("newton result: " + newton(x, n));
    }

    /**
     * 牛顿迭代法
     * xn+1 = xn-f(xn)/f'(xn)
     *
     * 需要求解的问题为：f(x)=x2-2 的零点
     *
     * @param x 某个数
     * @param n 精确到小数点后的位数
     * @return double 最终结果
     */
    private double newton(double x, int n) {

//        double epsilon = Math.pow(0.1, n);
        double epsilon = 0.0000000001;
        System.out.println("epsilon: " + epsilon);

        if (Math.abs(Math.pow(x, 2) - 2) > epsilon) {
            x = x - (Math.pow(x, 2) - 2) / (2 * x);
            return newton(x, n);
        }

        return x;
    }
}

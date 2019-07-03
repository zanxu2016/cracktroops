package info.luckydog.algorithm.topics;

import org.junit.Test;

/**
 * 判断一个数是否为2的幂次
 * 位运算，效率高
 *
 * @author eric
 * @since 2019/7/1
 */
public class PowerOf2 {

    @Test
    public void powerOf2Test() {
        System.out.println(isPowerOf2(100L));
        System.out.println(isPowerOf2(128L));

        System.out.println(isPowerOfTwo(100L));
        System.out.println(isPowerOfTwo(128L));
    }

    /**
     * 8    1000
     * 7    0111
     * 8 & (8-1) = 0
     *
     * @param n 判断数
     * @return boolean
     */
    private boolean isPowerOf2(long n) {
        return (n & (n - 1)) == 0;
    }

    /**
     * 16   0001 0000
     * -16  1111 0000
     * (16 & -16) = 16
     * @param n
     * @return
     */
    private boolean isPowerOfTwo(long n) {
        return (n & -n) == n;
    }
}

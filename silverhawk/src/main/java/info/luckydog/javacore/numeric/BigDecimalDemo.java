package info.luckydog.javacore.numeric;

import java.math.BigDecimal;

public class BigDecimalDemo {

    public static void main(String[] args) {
        // BigDecimal构造函数，有精度损失
        BigDecimal bd = new BigDecimal(0.1d);
        System.out.println(bd);// 0.1000000000000000055511151231257827021181583404541015625

        // 使用valueOf
        BigDecimal bd2 = BigDecimal.valueOf(0.1d);
        System.out.println(bd2);// 0.1
    }
}

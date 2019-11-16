package info.luckydog.javacore.numeric;

import org.junit.Test;

/**
 * IntegerTest
 *
 * @author eric
 * @since 2019/10/29
 */
public class IntegerTest {

    @Test
    public void test() {
        Integer a = 1;
        Integer b = 2;
        Integer c = 1;
        Integer d = null;

        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(a.equals(c));
        System.out.println(a == d);
        System.out.println(a.equals(d));
    }
}

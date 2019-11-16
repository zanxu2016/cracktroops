package info.luckydog.javacore.object_interface;

import org.junit.Test;

/**
 * ObjectTest
 *
 * @author eric
 * @since 2019/10/16
 */
public class ObjectTest {

    @Test
    public void test() {
        Shape rectangle = new Rectangle();

        Shape circle = new Circle();

        rectangle.area();
        circle.area();
    }
}

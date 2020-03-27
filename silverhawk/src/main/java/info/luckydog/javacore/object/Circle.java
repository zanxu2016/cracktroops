package info.luckydog.javacore.object;

/**
 * Circle
 *
 * @author eric
 * @since 2019/10/16
 */
public class Circle implements Shape {

    private int radius;

    @Override
    public double area() {
        return Math.PI * Math.pow(radius, 2);
    }
}

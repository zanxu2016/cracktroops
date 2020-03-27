package info.luckydog.javacore.object;

/**
 * Rectangle
 *
 * @author eric
 * @since 2019/10/16
 */
public class Rectangle implements Shape {

    private int side;
    private int height;

    Rectangle() {

    }

    Rectangle(int side, int height) {
        this.side = side;
        this.height = height;
    }

    @Override
    public double area() {
        return side * height / 2;
    }
}

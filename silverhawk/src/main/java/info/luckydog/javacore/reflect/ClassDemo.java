package info.luckydog.javacore.reflect;

/**
 * ClassDemo
 *
 * @author eric
 * @since 2019/12/11
 */
public class ClassDemo {
    public static void main(String[] args) {
        ClassDemo classDemo = new ClassDemo();

        // 全类名
        String fullClassName = classDemo.getClass().getName();
        System.out.println(fullClassName);
        // 类名
        String simpleClassName = classDemo.getClass().getSimpleName();
        System.out.println(simpleClassName);

    }
}

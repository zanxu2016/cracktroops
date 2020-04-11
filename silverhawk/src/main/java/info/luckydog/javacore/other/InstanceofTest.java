package info.luckydog.javacore.other;

import org.junit.Test;

/**
 * instanceof
 * a instanceof B
 * 判断a是否为B类的实例
 * 编译期，a的编译时类型是B类型、B类型父类、B类型子类，即可编译通过；
 * 运行期，a实际引用对象是B类型实例、B类型父类实例、B类型实现类，即可运行正常。
 */
public class InstanceofTest {

    @Test
    public void test1() {
        Object obj = "hello";
        System.out.println(obj instanceof Object);

        String str = (String) obj;
        System.out.println(str instanceof String);

        Object num = new Integer(1);
        System.out.println(num instanceof Object);

        System.out.println(num instanceof String);

//        System.out.println(str instanceof Math);// 编译失败

//        Math math = (Math) str;// 编译失败
    }
}

package info.luckydog.javacore.enums;

import java.util.Arrays;

/**
 * 枚举类
 * 使用规范：
 * 1、属性必须私有不可变
 * 2、构造器设置属性值
 * 3、只能有getter方法
 */
public class EnumDemo {

    public static void main(String[] args) {
        System.out.println(NumEnum.ONE);
        System.out.println(NumEnum.ONE.getCode());
        System.out.println(NumEnum.ONE.getName());
        System.out.println(NumEnum.ONE.compareTo(NumEnum.TWO));
        System.out.println(NumEnum.ONE.getDeclaringClass());

        System.out.println(NumEnum.valueOf("TWO"));

        System.out.println(Arrays.toString(NumEnum.values()));
    }
}


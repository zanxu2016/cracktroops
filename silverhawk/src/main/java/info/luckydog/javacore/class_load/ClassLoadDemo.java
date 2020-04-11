package info.luckydog.javacore.class_load;

/**
 * ClassLoadDemo
 *
 * @author eric
 * @since 2019/12/12
 */
public class ClassLoadDemo {

    public static void main(String[] args) throws ClassNotFoundException {
//        Cat cat = new Cat();// 加载类
//        Class c_cat2 = Class.forName("info.luckydog.javacore.class_load.Cat");// 加载类
//        System.out.println(Cat.age);// 加载父类和子类

//        Class c_cat2 = Class.forName("info.luckydog.javacore.class_load.Cat", false, Cat.class.getClassLoader());// 未加载类
//        Class c_cat = Cat.class;// 未加载类
//        System.out.println(Cat.num);// 未加载类
//        System.out.println(Cat.legs);// 未加载父类和子类
//        new ClassLoader(){}.loadClass("info.luckydog.javacore.class_load.Cat");// 未加载类
//        Cat[] cats = new Cat[2];// 未加载类


    }
}

class Cat extends Animal {
    final static int num = 10;
    static int age = 10;

    static {
        System.out.println("Cat is loaded.");
    }
}

class Animal {
    static final int legs = 4;

    static {
        System.out.println("Animal is loaded.");
    }
}

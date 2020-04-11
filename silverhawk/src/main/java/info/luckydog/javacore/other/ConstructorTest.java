package info.luckydog.javacore.other;

import org.junit.Test;

import java.io.*;

public class ConstructorTest {

    @Test
    public void testCat() {
        /*
        1、new 关键字为cat对象申请内存空间；
        2、Cat构造器初始化cat对象。

        无须调用构造器的情况：
        1、反序列化；
        2、clone
         */
        Cat cat = new Cat();
        System.out.println(cat.getName());
    }

    @Test
    public void testDog() throws IOException {
        Dog dog = new Dog("二哈");
        System.out.println("dog 对象创建成功");
        Dog dog2 = null;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("a.bin")); ObjectInputStream ois = new ObjectInputStream(new FileInputStream("a.bin"))) {
            // 序列化输出对象
            oos.writeObject(dog);
            oos.flush();
            // 反序列化恢复对象
            dog2 = (Dog) ois.readObject();
            // 两个对象的实例变量值完全相等，下面输出true
            System.out.println(dog.equals(dog2));
            // 两个对象不相同，下面输出false（破坏单例类的规则，可以为单例类提供readResolve()方法，保证反序列化时得到已有Java实例）
            System.out.println(dog == dog2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRat() {
        Rat rat = new Rat("Jerry");
        System.out.println("rat 对象创建成功");
        // 采用 clone() 方法复制一个新的对象
        Rat jerry = (Rat) rat.clone();
        // 两个对象的实例变量值完全相等，输出true
        System.out.println(jerry.equals(rat));
        // 两个对象不同，输出false
        System.out.println(jerry == rat);
    }
}

// 测试无参构造器返回参加void
class Cat {
    private String name;
    private String color;

    public Cat() {// 无参构造器，用于初始化对象
        this.name = "Tom";
        this.color = "grey";
    }

    public void Cat() {// 无参构造器，加上void，变为普通方法
        this.name = "Tom";
        this.color = "grey";
    }

    public String getName() {
        return name;
    }
}

// 测试反序列化不调用构造器
class Dog implements Serializable {
    private String name;

    public Dog(String name) {
        System.out.println("Dog(String name) constructor.");
        this.name = name;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj.getClass() == Dog.class) {
            Dog target = (Dog) obj;
            return target.name.equals(this.name);
        }
        return false;
    }

    public int hashCode() {
        return name.hashCode();
    }
}

class Rat implements Cloneable {
    private String name;

    public Rat(String name) {
        System.out.println("调用有参构造器");
        this.name = name;
    }

    public Object clone() {
        Rat clone = null;
        try {
            clone = (Rat) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj.getClass() == Rat.class) {
            Rat target = (Rat) obj;
            return target.name.equals(this.name);
        }
        return false;
    }

    public int hasCode() {
        return this.name.hashCode();
    }
}

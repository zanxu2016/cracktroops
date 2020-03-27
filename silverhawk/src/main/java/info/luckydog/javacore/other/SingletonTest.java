package info.luckydog.javacore.other;

import org.junit.Test;

import java.io.*;

public class SingletonTest {

    @Test
    public void test() {
        Singleton singleton = Singleton.getInstance("金毛");
        System.out.println("创建对象成功");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("a.bin")); ObjectInputStream ois = new ObjectInputStream(new FileInputStream("a.bin"))) {
            // 序列化输出对象
            oos.writeObject(singleton);
            oos.flush();
            // 反序列化恢复对象
            Singleton singleton2 = (Singleton) ois.readObject();
            // 对比两个对象，输出true
            System.out.println(singleton == singleton2);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class Singleton implements Serializable {
    private static Singleton instance;
    private String name;

    private Singleton(String name) {
        System.out.println("调用有参构造器");
        this.name = name;
    }

    public static Singleton getInstance(String name) {
        // 只有当instance为null时才创建该对象
        if (instance == null) {
            instance = new Singleton(name);
        }
        return instance;
    }

    // 提供 readResolve() 方法
    private Object readResolve() throws ObjectStreamException {
        // 得到已有的instance实例
        return instance;
    }
}

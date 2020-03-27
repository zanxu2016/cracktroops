package info.luckydog.javacore.other;

/**
 * 使用jhsdb命令，查找staticObj,instanceObj,localObj引用在虚拟机中的位置
 * 预期：staticObj->方法区，instanceObj->堆，localObj->栈
 * 需要jdk9才有jhsdb命令工具
 * 具体操作见《深入理解Java虚拟机：JVM高级特性与最佳实践（第3版）-周志明》4.3.1
 */
public class JHSDB_TestCase {
    static class Test {
        static ObjectHolder staticObj = new ObjectHolder();
        ObjectHolder instanceObj = new ObjectHolder();

        void foo() {
            ObjectHolder localObj = new ObjectHolder();
            System.out.println("done");// 此处设断点
        }
    }

    private static class ObjectHolder {
    }

    public static void main(String[] args) {
        Test test = new JHSDB_TestCase.Test();
        test.foo();
    }
}

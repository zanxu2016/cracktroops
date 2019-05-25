package info.luckydog.design_pattern.singleton;

public class Class {

    private Class(){}
    static {
        System.out.println("This's class's static code block");
    }

    private static class InnerClass{
        private static Class clazz = new Class();
        static {
            System.out.println("This's innerClass's static code block");
        }
    }

    public static Class getInstance(){
        return InnerClass.clazz;
    }

}

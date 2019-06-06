package info.luckydog.design_pattern.proxy;

/**
 * RealSubject
 *
 * @author eric
 * @since 2019/6/1
 */
public class RealSubject implements Subject {

    private String name;

    //实际执行者不能创建对象，而是由代理创建
    public RealSubject(Subject subject, String name) {
        try {
            if (subject == null) throw new Exception("subject is null");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.name = name;
    }

    @Override
    public void doSomething() {
        System.out.println(this.name + " do something...");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

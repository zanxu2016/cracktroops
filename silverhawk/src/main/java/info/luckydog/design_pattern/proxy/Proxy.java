package info.luckydog.design_pattern.proxy;

/**
 * Proxy
 *
 * @author eric
 * @since 2019/6/1
 */
public class Proxy implements Subject {

    private Subject subject;

    //代理创建被代理者
    public Proxy(String name) {
        subject = new RealSubject(this, name);
    }

    @Override
    public void doSomething() {
        doBefore();
        this.subject.doSomething();
        doAfter();
    }

    public void doBefore(){
        System.out.println("proxy do before...");
    }

    public void doAfter(){
        System.out.println("proxy do after...");
    }
}

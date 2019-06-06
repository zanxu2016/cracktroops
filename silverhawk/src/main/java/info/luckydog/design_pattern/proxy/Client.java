package info.luckydog.design_pattern.proxy;

/**
 * Client
 *
 * @author eric
 * @since 2019/6/1
 */
public class Client {

    public static void main(String[] args) {
        Subject proxy = new Proxy("测试者");
        proxy.doSomething();
    }
}

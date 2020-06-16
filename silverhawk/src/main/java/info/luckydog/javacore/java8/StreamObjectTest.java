package info.luckydog.javacore.java8;

import com.github.houbb.junitperf.core.annotation.JunitPerfConfig;
import com.github.houbb.junitperf.core.report.impl.HtmlReporter;
import org.junit.jupiter.api.BeforeAll;

import java.util.*;
import java.util.stream.Collectors;

public class StreamObjectTest {

    public static List<Order> orders;

    @BeforeAll
    public static void init() {
        orders = Order.genOrders(10);
    }

    @JunitPerfConfig(duration = 10000, warmUp = 1000, reporter = {HtmlReporter.class})
    public void testSumOrderForLoop() {
        Map<String, Double> map = new HashMap<>();
        for (Order od : orders) {
            String userName = od.getUserName();
            Double v;
            if ((v = map.get(userName)) != null) {
                map.put(userName, v + od.getPrice());
            } else {
                map.put(userName, od.getPrice());
            }
        }

    }

    @JunitPerfConfig(duration = 10000, warmUp = 1000, reporter = {HtmlReporter.class})
    public void testSumOrderStream() {
        orders.stream().collect(
                Collectors.groupingBy(Order::getUserName,
                        Collectors.summingDouble(Order::getPrice)));
    }

    @JunitPerfConfig(duration = 10000, warmUp = 1000, reporter = {HtmlReporter.class})
    public void testSumOrderParallelStream() {
        orders.parallelStream().collect(
                Collectors.groupingBy(Order::getUserName,
                        Collectors.summingDouble(Order::getPrice)));
    }
}


class Order {
    private String userName;
    private double price;
    private long timestamp;

    public Order(String userName, double price, long timestamp) {
        this.userName = userName;
        this.price = price;
        this.timestamp = timestamp;
    }

    public String getUserName() {
        return userName;
    }

    public double getPrice() {
        return price;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public static List<Order> genOrders(int listLength) {
        ArrayList<Order> list = new ArrayList<>(listLength);
        Random rand = new Random();
        int users = listLength / 200;// 200 orders per user
        users = users == 0 ? listLength : users;
        ArrayList<String> userNames = new ArrayList<>(users);
        for (int i = 0; i < users; i++) {
            userNames.add(UUID.randomUUID().toString());
        }
        for (int i = 0; i < listLength; i++) {
            double price = rand.nextInt(1000);
            String userName = userNames.get(rand.nextInt(users));
            list.add(new Order(userName, price, System.nanoTime()));
        }
        return list;
    }

    @Override
    public String toString() {
        return userName + "::" + price;
    }
}

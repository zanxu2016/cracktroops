package info.luckydog.javacore.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMapDemo
 *
 * @author eric
 * @since 2019/12/12
 */
public class HashMapDemo {

    private static final int MAXIMUM_CAPACITY = 1 << 30;

    // HashMap 中算法：返回大于参数的最小的2幂次
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public static void main(String[] args) {
        int cap = 10;
        System.out.println(tableSizeFor(cap));
        cap = 20;
        System.out.println(tableSizeFor(cap));
        cap = 1025;
        System.out.println(tableSizeFor(cap));

        // 初始化map
        Map<String, String> map = new HashMap<String, String>() {{
            this.put("1", "a");
            this.put("2", "b");
            this.put("3", "c");
            this.put("4", "d");
        }};
        // 遍历map
        foreachMap(map);
    }

    /**
     * 遍历map，本质上是对entrySet进行遍历，元素Entry包含key和value
     *
     * @param map
     */
    public static void foreachMap(Map<String, String> map) {
        System.out.println("key : value");
        map.forEach((key, value) -> System.out.println(key + " : " + value));
    }
}

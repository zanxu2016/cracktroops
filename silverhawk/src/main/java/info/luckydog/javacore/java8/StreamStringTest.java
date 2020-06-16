package info.luckydog.javacore.java8;

import com.github.houbb.junitperf.core.annotation.JunitPerfConfig;
import com.github.houbb.junitperf.core.report.impl.HtmlReporter;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.Random;

public class StreamStringTest {

    public static ArrayList<String> list;

    @BeforeAll
    public static void init() {
        list = randomStringList(1000000);
    }

    @JunitPerfConfig(duration = 10000, warmUp = 1000, reporter = {HtmlReporter.class})
    public void testMinStringForLoop() {
        String minStr = null;
        boolean first = true;
        for (String str : list) {
            if (first) {
                first = false;
                minStr = str;
            }
            if (minStr.compareTo(str) > 0) {
                minStr = str;
            }
        }
    }

    @JunitPerfConfig(duration = 10000, warmUp = 1000, reporter = {HtmlReporter.class})
    public void textMinStringStream() {
        list.stream().min(String::compareTo).get();
    }

    @JunitPerfConfig(duration = 10000, warmUp = 1000, reporter = {HtmlReporter.class})
    public void testMinStringParallelStream() {
        list.stream().parallel().min(String::compareTo).get();
    }

    private static ArrayList<String> randomStringList(int listLength) {
        ArrayList<String> list = new ArrayList<>(listLength);
        Random rand = new Random();
        int strLength = 10;
        StringBuilder buf = new StringBuilder(strLength);
        for (int i = 0; i < listLength; i++) {
            buf.delete(0, buf.length());
            for (int j = 0; j < strLength; j++) {
                buf.append((char) ('a' + rand.nextInt(26)));
            }
            list.add(buf.toString());
        }
        return list;
    }
}

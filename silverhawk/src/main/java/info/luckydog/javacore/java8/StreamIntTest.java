package info.luckydog.javacore.java8;

import com.github.houbb.junitperf.core.annotation.JunitPerfConfig;
import com.github.houbb.junitperf.core.report.impl.HtmlReporter;
import org.junit.jupiter.api.BeforeAll;

import java.util.Arrays;
import java.util.Random;

public class StreamIntTest {

    public static int[] arr;

    @BeforeAll
    public static void init() {
        arr = new int[500000000];  //5亿个随机Int
        randomInt(arr);
    }

    @JunitPerfConfig(warmUp = 1000, reporter = {HtmlReporter.class})
    public void testIntFor() {
        minIntFor(arr);
    }

    @JunitPerfConfig(warmUp = 1000, reporter = {HtmlReporter.class})
    public void testIntParallelStream() {
        minIntParallelStream(arr);
    }

    @JunitPerfConfig(warmUp = 1000, reporter = {HtmlReporter.class})
    public void testIntStream() {
        minIntStream(arr);
    }

    private int minIntStream(int[] arr) {
        return Arrays.stream(arr).min().getAsInt();
    }

    private int minIntParallelStream(int[] arr) {
        return Arrays.stream(arr).parallel().min().getAsInt();
    }

    private int minIntFor(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int anArr : arr) {
            if (anArr < min) {
                min = anArr;
            }
        }
        return min;
    }

    private static void randomInt(int[] arr) {
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt();
        }
    }
}

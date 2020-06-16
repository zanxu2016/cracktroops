package info.luckydog.javacore.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ListDemo {

    @Test
    public void listFromArrayTest() {
        String[] strArr = {"java", "python", "c", "c#", "php"};
        List<String> strList = Arrays.asList(strArr);

//        strList.add("scala");// 会抛异常 UnsupportedOperationException
        strList.set(2, "kotlin");

        strArr[3] = "scala";

        System.out.println(Arrays.toString(strArr));
        System.out.println(String.join(",", strList));

        System.out.println(strArr[0].equals(strList.get(0)));


    }


}

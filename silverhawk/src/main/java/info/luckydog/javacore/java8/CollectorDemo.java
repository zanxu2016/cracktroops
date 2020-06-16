package info.luckydog.javacore.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectorDemo {

    @Test
    public void groupByTest() {
        Stu stuA = new Stu(1, "eric", 20, 1);
        Stu stuB = new Stu(2, "lily", 18, 2);
        Stu stuC = new Stu(3, "lucy", 18, 2);
        Stu stuD = new Stu(4, "harry", 19, 1);
        Stu stuE = new Stu(5, "tom", 21, 1);

        List<Stu> stuList = Arrays.asList(stuA, stuB, stuC, stuD, stuE);

        Map<Integer, List<Stu>> listMap = stuList.stream().collect(Collectors.groupingBy(Stu::getClassNo));
        System.out.println(listMap);

        Map<Integer, Long> collect = stuList.stream().collect(Collectors.groupingBy(Stu::getClassNo, Collectors.counting()));
        System.out.println(collect);
    }
}

@Data
@AllArgsConstructor
class Stu {
    private int id;
    private String name;
    private int age;
    private int classNo;
}

@Data
class StuCount {
    private int classNo;
    private int count;
}

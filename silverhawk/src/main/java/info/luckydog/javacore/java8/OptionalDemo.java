package info.luckydog.javacore.java8;

import lombok.Data;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class OptionalDemo {

    @Test
    public void test() {
        ClassRoom classRoom = null;
        List<Student> students = Optional.ofNullable(classRoom).orElse(null).getStudents();
        System.out.println(students.size());
    }

    @Data
    static class ClassRoom {
        private List<Student> students;
    }

    @Data
    static class Student {
        private String name;
    }
}

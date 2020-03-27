package info.luckydog.javacore.object;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 讨论 == equals hashCode 之间的关系和区别
 * <p>
 * ==
 * 比较两个对象的内存地址
 * <p>
 * equals
 * Object对象中默认与==相同
 * 可被覆写
 * <p>
 * hashCode
 * Object对象中默认是native方法
 * 可被覆写
 * 在散列表中有用，其他地方暂时不需要hashCode
 * <p>
 * 注意：
 * 若hashCode不被覆写，或，覆写逻辑与equals不一致，则equals为true的两个对象，hashCode未必相同；
 * 散列表中，若两个对象的hashCode相同，就会有哈希冲突现象，但这两个对象的equals未必为true。
 */
public class EqualsDemo {

    public static void main(String[] args) {
        Person a = new Person("a", 20, 'F');
        Person b = new Person("a", 20, 'F');
        Person c = new Person("c", 20, 'F');

        System.out.println("a==b " + (a == b));
        System.out.println("a equals b " + a.equals(b));
        System.out.println("a hashCode b " + (a.hashCode() == b.hashCode()));
        System.out.println("============================");
        System.out.println("a==c " + (a == b));
        System.out.println("a equals c " + a.equals(c));
        System.out.println("a hashCode c " + (a.hashCode() == c.hashCode()));
        System.out.println("============================");

        Set<Person> set = new HashSet<>();
        set.add(a);
        set.add(b);
        set.add(c);
        set.forEach(System.out::println);
    }
}

class Person {
    private String name;
    private int age;
    private char gender;

    public Person(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    // equals和hashCode的覆写逻辑一致，equals为true时，hashCode一定相同
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name) &&
                Objects.equals(gender, person.gender);
    }

    // hashCode不被覆写，或，覆写逻辑与equals不一致，则equals为true的两个对象，hashCode未必相同
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Person person = (Person) o;
//        return age == person.age &&
//                Objects.equals(name, person.name);
//    }


    @Override
    public int hashCode() {
        return Objects.hash(name, age, gender);
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}

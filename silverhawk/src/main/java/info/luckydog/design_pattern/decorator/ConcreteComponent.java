package info.luckydog.design_pattern.decorator;

public class ConcreteComponent implements Component {
    @Override
    public void operate() {
        System.out.println("ConcreteComponent operate...");
    }
}

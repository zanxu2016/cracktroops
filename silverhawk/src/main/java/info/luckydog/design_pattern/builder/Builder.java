package info.luckydog.design_pattern.builder;

public interface Builder {

    Product buildProduct();

    void createPart1();

    void createPart2();

    void createPart3();
}

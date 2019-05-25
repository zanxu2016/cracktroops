package info.luckydog.design_pattern.builder;

public class Director {

    public Director() {
    }

    public Product buildProduct(Builder builder) {
        return builder.buildProduct();
    }
}

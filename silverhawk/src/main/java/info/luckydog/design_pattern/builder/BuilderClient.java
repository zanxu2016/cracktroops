package info.luckydog.design_pattern.builder;

public class BuilderClient {

    public static void main(String[] args) {
        Director director = new Director();

        director.buildProduct(new ProductBuilder());
    }
}

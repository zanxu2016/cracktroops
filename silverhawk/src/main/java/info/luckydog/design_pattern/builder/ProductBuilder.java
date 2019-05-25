package info.luckydog.design_pattern.builder;

public class ProductBuilder implements Builder {

    private Product product;

    public Product buildProduct() {
        System.out.println("build product...");
        product = new Product();
        createPart1();
        createPart2();
        createPart3();
        return product;
    }

    public void createPart1() {
        System.out.println("createPart1");
        product.setPart1("part1");
    }

    public void createPart2() {
        System.out.println("createPart2");
        product.setPart2("part2");
    }

    public void createPart3() {
        System.out.println("createPart3");
        product.setPart3("part3");
    }
}

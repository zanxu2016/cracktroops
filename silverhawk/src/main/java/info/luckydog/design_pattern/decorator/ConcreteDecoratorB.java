package info.luckydog.design_pattern.decorator;

public class ConcreteDecoratorB extends Decorator {
    public ConcreteDecoratorB(Component component) {
        super(component);
    }

    private void decorate() {
        System.out.println("Decorator B decorate...");
    }

    @Override
    public void operate() {
        super.operate();
        this.decorate();
    }
}

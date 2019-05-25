package info.luckydog.design_pattern.decorator;

public class ConcreteDecoratorA extends Decorator {
    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    private void decorate() {
        System.out.println("Decorator A decorate...");
    }

    @Override
    public void operate() {
        this.decorate();
        super.operate();
    }
}

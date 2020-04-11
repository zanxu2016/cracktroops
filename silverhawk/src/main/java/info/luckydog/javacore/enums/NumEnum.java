package info.luckydog.javacore.enums;

public enum NumEnum {
    ONE(1, "one"),
    TWO(2, "two"),
    THREE(3, "three"),
    ;

    NumEnum(final int code, final String name) {
        this.code = code;
        this.name = name;
    }

    private final int code;
    private final String name;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}

package info.luckydog.design_pattern.strategy;

public class StrategyClient {
    public static void main(String[] args) {
        Validator numericValidator = new Validator(new IsNumeric());
        boolean isNumeric = numericValidator.validate("aaa");
        System.out.println(isNumeric);

        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        boolean lowerCase = lowerCaseValidator.validate("bbb");
        System.out.println(lowerCase);


        //用lambda表达式，简化策略模式，ValidationStrategy 是个函数式接口
        Validator numeric = new Validator((String a) -> a.matches("\\d+"));
        boolean isNum = numeric.validate("aaa");
        System.out.println(isNum);

        Validator allLowercase = new Validator((String a) -> a.matches("[a-z]+"));
        boolean isAllLowerCase = allLowercase.validate("bbb");
        System.out.println(isAllLowerCase);

        //使用策略枚举
        boolean validCase = StrategyEnum.IS_ALL_LOWER_CASE.execute("aaa");
        System.out.println(validCase);
        boolean validNumeric = StrategyEnum.IS_NUMERIC.execute("bbb");
        System.out.println(validNumeric);

        String a = "aaa";
        String b = "bbb";
        String strategyNameA = "IS_ALL_LOWER_CASE";
        String strategyNameB = "IS_NUMERIC";

        System.out.println(StrategyEnum.valueOf(strategyNameA).execute(a));
        System.out.println(StrategyEnum.valueOf(strategyNameB).execute(b));

        System.out.println(StrategyEnum.valueOf(""));
    }
}

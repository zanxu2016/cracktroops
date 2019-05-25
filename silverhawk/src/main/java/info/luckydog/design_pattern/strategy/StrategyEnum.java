package info.luckydog.design_pattern.strategy;

public enum StrategyEnum {

    IS_ALL_LOWER_CASE() {
        @Override
        public boolean execute(String str) {
            return str.matches("[a-z]+");
        }
    },
    IS_NUMERIC() {
        @Override
        public boolean execute(String str) {
            return str.matches("\\d+");
        }
    };


    StrategyEnum() {
    }

    public abstract boolean execute(String str);
}

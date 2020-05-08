package emg.demo.jiact.cap9;

public class StrategyMain {


    public boolean validateNumeric(String input) {
        return validate(new Validator(new IsNumeric()), input);
    }

    public boolean validateLowercase(String input) {
        return validate(new Validator(new IsAllLowerCase()), input);
    }

    public boolean validate(Validator validator, String input) {
        return validator.validate(input);
    }

    interface ValidationStrategy {
        boolean execute(String s);
    }

    static private class IsAllLowerCase implements ValidationStrategy {

        @Override
        public boolean execute(String s) {
            return s.matches("[a-z]+");
        }

    }

    static private class IsNumeric implements ValidationStrategy {

        @Override
        public boolean execute(String s) {
            return s.matches("\\d+");
        }

    }

    static private class Validator {

        private final ValidationStrategy strategy;

        public Validator(ValidationStrategy v) {
            strategy = v;
        }

        public boolean validate(String s) {
            return strategy.execute(s);
        }
    }
}


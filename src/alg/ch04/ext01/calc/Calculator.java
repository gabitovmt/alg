package alg.ch04.ext01.calc;

public class Calculator {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        var expr = "15 * 30 + 17 * 34";
        var calculator = new Calculator();
        var result = calculator.calc(expr);
        System.out.printf("%s = %d%n", expr, result);
    }

    public int calc(String expression) {
        var parser = new Parser();
        var evaluator = new Evaluator();

        return evaluator.eval(parser.parse(expression));
    }
}

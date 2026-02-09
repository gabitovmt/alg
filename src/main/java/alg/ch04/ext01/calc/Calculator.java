package alg.ch04.ext01.calc;

public class Calculator {

    public int calc(String expression) {
        var parser = new Parser();
        var evaluator = new Evaluator();

        return evaluator.eval(parser.parse(expression));
    }
}

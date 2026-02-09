package alg.ch04.ext01.calc;

public class Evaluator {
    private static final int DEFAULT_STACK_SIZE = 20;

    int eval(ExprElement[] expr) {
        var result = new Stack(DEFAULT_STACK_SIZE);

        for (var el : expr) {
            if (el.isOperand()) {
                result.push(el);
            } else {
                var b = result.pop();
                var a = result.pop();
                result.push(calc(a, b, el));
            }
        }

        return result.pop().operand();
    }

    private ExprElement calc(ExprElement operand1, ExprElement operand2, ExprElement operator) {
        int a = operand1.operand();
        int b = operand2.operand();
        int op = operator.operator();

        int c = switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> a / b;
            case '^' -> (int) Math.pow(a, b);
            default -> throw new UnsupportedException();
        };

        return new ExprElement(c);
    }
}

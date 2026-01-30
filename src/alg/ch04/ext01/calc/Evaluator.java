package alg.ch04.ext01.calc;

public class Evaluator {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        var expr = new ExprElement[] {
                new ExprElement(15),
                new ExprElement(30),
                new ExprElement('*'),
                new ExprElement(17),
                new ExprElement(34),
                new ExprElement('*'),
                new ExprElement('+'),
        };

        var evaluator = new Evaluator();
        System.out.println(evaluator.eval(expr));
    }

    private static final int DEFAULT_STACK_SIZE = 10;

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

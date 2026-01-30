package alg.ch04.ext01.calc;

import java.util.Arrays;

class Parser {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        var parser = new Parser();
        var postfix = parser.parse("15 * 30 + 17 * 34");
        System.out.println(Arrays.toString(postfix));
    }

    private static final int DEFAULT_STACK_SIZE = 10;
    private Stack result;
    private Stack operators;
    private char prev;

    ExprElement[] parse(String expression) {
        result = new Stack(DEFAULT_STACK_SIZE);
        operators = new Stack(DEFAULT_STACK_SIZE);

        char[] chars = expression.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            switch (ch) {
                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9':
                    parseNumber(ch);
                    break;
                case '+', '-', '/', '*', '^':
                    parseOperator(ch);
                    break;
                case '(':
                    operators.push(new ExprElement(ch));
                    break;
                case ')':
                    parseCloseBracket();
                    break;
                case ' ':
                    break;
                default:
                    throw new IllegalArgumentException(String.format("Illegal character '%s' at index %d", ch, i));
            }
            prev = ch;
        }

        while (!operators.isEmpty()) {
            result.push(operators.pop());
        }

        return result.toArray();
    }

    private void parseNumber(char ch) {
        int num = ch - '0';
        if (prev >= '0' && prev <= '9') {
            num += result.pop().operand() * 10;
        }
        result.push(new ExprElement(num));
    }

    private void parseOperator(char op) {
        if (operators.isEmpty() || operatorPriority(operators.peek().operator()) < operatorPriority(op)) {
            operators.push(new ExprElement(op));
        } else {
            result.push(operators.pop());
            operators.push(new ExprElement(op));
        }
    }

    private int operatorPriority(char op) {
        return switch (op) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            default -> throw new UnsupportedException();
        };
    }

    private void parseCloseBracket() {
        ExprElement el;
        while ((el = operators.pop()).operator() != '(') {
            result.push(el);
        }
    }
}

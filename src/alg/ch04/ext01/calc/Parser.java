package alg.ch04.ext01.calc;

class Parser {
    private static final int DEFAULT_STACK_SIZE = 20;
    private Stack result;
    private Stack operators;
    private ElementType typePrev;

    ExprElement[] parse(String expression) {
        result = new Stack(DEFAULT_STACK_SIZE);
        operators = new Stack(DEFAULT_STACK_SIZE);
        typePrev = ElementType.NONE;

        char[] chars = expression.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            switch (ch) {
                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9':
                    parseNumber(ch);
                    typePrev = ElementType.NUMBER;
                    break;
                case '+', '/', '*', '^':
                    parseOperator(ch);
                    typePrev = ElementType.OPERATOR;
                    break;
                case '-':
                    if (typePrev == ElementType.NONE || typePrev == ElementType.OPEN_BRACKET) {
                        parseNumber(ch);
                        typePrev = ElementType.NUMBER;
                    } else {
                        parseOperator(ch);
                        typePrev = ElementType.OPERATOR;
                    }
                    break;
                case '(':
                    operators.push(new ExprElement(ch));
                    typePrev = ElementType.OPEN_BRACKET;
                    break;
                case ')':
                    parseCloseBracket();
                    typePrev = ElementType.CLOSE_BRACKET;
                    break;
                case ' ':
                    break;
                default:
                    throw new IllegalArgumentException(String.format("Illegal character '%s' at index %d", ch, i));
            }
        }

        while (!operators.isEmpty()) {
            result.push(operators.pop());
        }

        return result.toArray();
    }

    private void parseNumber(char ch) {
        if (ch == '-') {
            result.push(new ExprElement(0, true));
            return;
        }

        int num = ch - '0';
        if (typePrev == ElementType.NUMBER) {
            var o = result.pop();
            if (o.operand() == 0 && o.isNegative()) {
                num *= -1;
            } else {
                num += o.operand() * 10;
            }
        }
        result.push(new ExprElement(num));
    }

    private void parseOperator(char op) {
        while (!operators.isEmpty() && operators.peek().operator() != '('
                && operatorPriority(operators.peek().operator()) >= operatorPriority(op)) {
            result.push(operators.pop());
        }
        operators.push(new ExprElement(op));
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
        while (!operators.isEmpty() && (el = operators.pop()).operator() != '(') {
            result.push(el);
        }
    }
}

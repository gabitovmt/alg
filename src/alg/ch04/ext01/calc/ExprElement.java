package alg.ch04.ext01.calc;

class ExprElement {
    private final boolean isOperator;
    private final char operator;
    private final int operand;
    private final boolean isNegative;

    ExprElement(char operator) {
        isOperator = true;
        this.operator = operator;
        operand = 0;
        isNegative = false;
    }

    ExprElement(int operand) {
        isOperator = false;
        operator = 0;
        this.operand = operand;
        isNegative = operand < 0;
    }

    ExprElement(int operand, boolean isNegative) {
        isOperator = false;
        operator = 0;
        this.operand = operand;
        this.isNegative = isNegative;
    }

    boolean isOperand() {
        return !isOperator;
    }

    boolean isNegative() {
        return isNegative;
    }

    char operator() {
        if (isOperator) {
            return operator;
        }
        throw new InvalidElementType("The element is operand");
    }

    int operand() {
        if (!isOperator) {
            return operand;
        }
        throw new InvalidElementType("The element is operator");
    }

    @Override
    public String toString() {
        return isOperator ? Character.toString(operator) : Integer.toString(operand);
    }
}

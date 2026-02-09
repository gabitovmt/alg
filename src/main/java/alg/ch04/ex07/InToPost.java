package alg.ch04.ex07;

// Преобразование инфиксной записи в постфиксную
public class InToPost {
    private final StackX stack;
    private final String input;
    private final StringBuilder output = new StringBuilder();

    public InToPost(String in) {
        input = in;
        int stackSize = input.length();
        stack = new StackX(stackSize);
    }

    // Преобразование в постфиксную форму
    public String doTrans() {
        for (int j = 0; j < input.length(); j++) {
            char ch = input.charAt(j);
            // Диагностика
            stack.displayStack("For " + ch + " ");

            switch (ch) {
                case '+', '-':
                    // Извлечение операторов
                    gotOper(ch, 1);
                    break;
                case '*', '/':
                    // Извлечение операторов
                    gotOper(ch, 2);
                    break;
                case '(':
                    // Открывающую круглую скобку занести в стек
                    stack.push(ch);
                    break;
                case ')':
                    // Закрывающая круглая скобка. Извлечение операторов
                    gotParen();
                    break;
                default:
                    // Остаётся операнд. Записать в выходную строку
                    output.append(ch);
            }
        }

        // Извлечение оставшихся операторов
        while (!stack.isEmpty()) {
            // Диагностика
            stack.displayStack("While ");
            output.append(stack.pop());
        }

        // Диагностика
        stack.displayStack("End ");

        return output.toString();
    }

    private void gotOper(char opThis, int prec1) {
        // Чтение оператора из входной строки
        while (!stack.isEmpty()) {
            char opTop = stack.pop();
            if (opTop == '(' || prec(opTop) < prec1) {
                // Открывающая скобка => сохранить её
                // Приоритет нового оператора меньше приоритета старого => сохранить новый оператор
                stack.push(opTop);
                break;
            } else {
                // Приоритет нового оператора не меньше приоритета старого
                output.append(opTop);
            }
        }

        // Занесение в стек нового оператора
        stack.push(opThis);
    }

    // Определение приоритета оператора
    private int prec(int op) {
        return op == '+' || op == '-' ? 1 : 2;
    }

    private void gotParen() {
        // Прочитана закрывающая скобка
        while (!stack.isEmpty()) {
            char chx = stack.pop();
            if (chx == '(') {
                // Извлечён '(', прервать выполнение
                break;
            } else {
                // Извлечён оператор, вывести в постфиксную строку
                output.append(chx);
            }
        }
    }
}

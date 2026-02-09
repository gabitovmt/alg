package alg.ch04.ex08;

public class ParsePost {

    public int doParse(String input) {
        var stack = new StackX(20);
        int interAns;

        // Для каждого символа
        for (int j = 0; j < input.length(); j++) {
            char ch = input.charAt(j);
            // Диагностика
            stack.displayStack(ch + " ");
            if (ch >= '0' &&  ch <= '9') {
                // Цифру заносим в стек
                stack.push(ch - '0');
            } else {
                // Извлечение операндов
                int num2 = stack.pop();
                int num1 = stack.pop();
                // Выполнение арифметической операции
                interAns = switch (ch) {
                    case '+' -> num1 + num2;
                    case '-' -> num1 - num2;
                    case '*' -> num1 * num2;
                    case '/' -> num1 / num2;
                    default -> 0;
                };
                stack.push(interAns);
                // Занесение промежуточного результата в стек
            }
        }

        // Получение результата
        return stack.pop();
    }
}

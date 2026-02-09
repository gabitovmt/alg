package alg.ch04.ex03;

import alg.ch04.ex02.Stack;

public class BracketChecker {
    private final String input;

    public BracketChecker(String in) {
        input = in;
    }

    @SuppressWarnings("java:S106")
    public void check() {
        var stack = new Stack<Character>(input.length());

        // Последовательное чтение
        for (int j = 0; j < input.length(); j++) {
            // Чтение символа
            char ch = input.charAt(j);
            switch (ch) {
                // Открывающие скобки
                case '{', '[', '(':
                    stack.push(ch);
                    break;
                // Закрывающие скобки
                case '}', ']', ')':
                    if (!stack.isEmpty()) {
                        // Извлечь и проверить
                        char chx = stack.pop();
                        if (!isValid(chx, ch)) {
                            System.out.println("Error: " + ch + " at " + j);
                        }
                    } else {
                        // Преждевременная нехватка элементов
                        System.out.println("Error: " + ch + " at " + j);
                    }
                    break;
                default:
                    // Для других символов действия не выполняются
                    break;
            }
        }

        // В этой точке обработаны все символы
        if (!stack.isEmpty()) {
            System.out.println("Error: missing right delimiter");
        }
    }

    // Скобки корректные?
    private boolean isValid(char open, char close) {
        return open == '{' && close == '}' || open == '[' && close == ']' || open == '(' && close == ')';
    }
}

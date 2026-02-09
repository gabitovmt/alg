package alg.ch04.ex08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Разбор постфиксных арифметических выражений
public class PostfixApp {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.print("Enter postfix: ");
            var input = getString();
            if (input.isEmpty()) {
                // Нажата клавиша [Enter], выход
                break;
            }

            // Создание объекта для разбора выражения
            var aParser = new ParsePost();
            int output = aParser.doParse(input);

            System.out.printf("Evaluates to %d%n", output);
        }
    }

    // Чтение строки с клавиатуры
    private static String getString() throws IOException {
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }
}

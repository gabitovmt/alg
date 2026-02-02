package alg.ch04.ex07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InfixApp {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.print("Enter infix: ");
            var input = getString();
            if (input.isEmpty()) {
                // Нажата клавиша [Enter], выход
                break;
            }

            // Преобразование
            var trans = new InToPost(input);
            String output = trans.doTrans();

            System.out.printf("Postfix is %s%n", output);
        }
    }

    // Чтение строки с клавиатуры
    private static String getString() throws IOException {
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }
}

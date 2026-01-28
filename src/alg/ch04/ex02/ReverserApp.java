package alg.ch04.ex02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReverserApp {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.print("Enter a string: ");
            // Чтение строки с клавиатуры
            String input = getString();
            // Завершение, если [Enter]
            if (input.isEmpty()) {
                break;
            }

            var reverser = new Reverser(input);
            String output = reverser.doRev();
            System.out.println("Reversed: " + output);
        }
    }

    private static String getString() throws IOException {
        var isr = new InputStreamReader(System.in);
        var br = new BufferedReader(isr);

        return br.readLine();
    }
}

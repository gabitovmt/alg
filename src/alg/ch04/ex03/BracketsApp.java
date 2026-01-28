package alg.ch04.ex03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Использование стека для поиска парных скобок
public class BracketsApp {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.print("Enter string containing delimiters: ");
            String input = getString();
            if (input.isEmpty()) {
                break;
            }

            new BracketChecker(input).check();
        }
    }

    private static String getString() throws IOException {
        var isr = new InputStreamReader(System.in);
        var br = new BufferedReader(isr);

        return br.readLine();
    }
}

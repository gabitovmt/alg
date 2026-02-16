package alg.ch06.ex04;

import java.util.Scanner;

// Построение списка анаграмм
public class AnagramApp {
    private static int count;
    private static char[] arrChar;

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        System.out.print("Enter a word: ");
        String input = new Scanner(System.in).nextLine();
        arrChar = input.toCharArray();

        doAnagram(arrChar.length);
    }

    private static void doAnagram(int newSize) {
        if (newSize == 1) {
            // Слово слишком маленькое. Не продолжаем
            return;
        }
        // Для каждой позиции
        for (int i = 0; i < newSize; i++) {
            // построить анаграммы остальных букв
            doAnagram(newSize - 1);
            if (newSize == 2) {
                // Нужно вывести слово
                displayWord();
            }
            // Циклический сдвиг влево
            rotate(newSize);
        }
    }

    // Циклический сдвиг влево
    private static void rotate(int newSize) {
        // Сохранение первой буквы
        int position = arrChar.length - newSize;
        char temp = arrChar[position];
        // Сдвиг остальных букв
        for (int i = position + 1; i < arrChar.length; i++) {
            arrChar[i - 1] = arrChar[i];
        }
        // Перемещение первой буквы на правый край
        arrChar[arrChar.length - 1] = temp;
    }

    @SuppressWarnings("java:S106")
    private static void displayWord() {
        System.out.printf("%3d %s ", ++count, new String(arrChar));
        if (count % 24 == 0) {
            System.out.println();
        }
    }
}

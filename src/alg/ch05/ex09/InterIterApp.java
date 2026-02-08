package alg.ch05.ex09;

import java.util.Scanner;

// Использование итератора связанного списка
public class InterIterApp {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        var list = new LinkedList();
        var iter = list.getIterator();

        iter.insertAfter(20);
        iter.insertAfter(40);
        iter.insertAfter(80);
        iter.insertBefore(60);

        var scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter first letter of exit, show, reset, next, get, before, after, delete: ");
            String choice = scanner.next();

            switch (choice) {
                case "e":
                    return;
                case "s":
                    System.out.println(list);
                    break;
                default:
                    System.out.println("Invalid entry");
            }
        }
    }
}

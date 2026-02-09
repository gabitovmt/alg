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
            System.out.print("Enter first letter of exit, show, reset, next, get, before, after, delete: ");
            String choice = scanner.next();

            switch (choice) {
                case "e": // Выход
                    return;
                case "s": // Вывод списка
                    System.out.println(list);
                    break;
                case "r": // Возврат к первому элементу
                    iter.reset();
                    break;
                case "n": // Переход к следующему элементу
                    if (iter.atEnd()) {
                        System.out.println("Can't go to next link");
                    } else {
                        iter.next();
                    }
                    break;
                case "g": // Получение текущего элемента
                    if (list.isEmpty()) {
                        System.out.println("List is empty");
                    } else {
                        System.out.println("Returned " + iter.getCurrent());
                    }
                    break;
                case "b": // Вставка перед текущим элементом
                    System.out.print("Enter value to insert: ");
                    iter.insertBefore(scanner.nextLong());
                    break;
                case "a": // Вставка после текущего элемента
                    System.out.print("Enter value to insert: ");
                    iter.insertAfter(scanner.nextLong());
                    break;
                case "d":
                    if (list.isEmpty()) {
                        System.out.println("Can't delete");
                    } else {
                        System.out.println("Deleted " + iter.remove());
                    }
                    break;
                default:
                    System.out.println("Invalid entry");
            }
        }
    }
}

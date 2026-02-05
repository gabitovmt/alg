package alg.ch05.ex01;

// Работа со связанным списком
public class LinkListApp {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        // Создание нового списка и вставка четырёх элементов
        var list = new LinkList();
        list.insertFirst(22, 2.99);
        list.insertFirst(44, 4.99);
        list.insertFirst(66, 6.99);
        list.insertFirst(88, 8.99);

        // Вывод содержимого списка
        list.displayList();

        // Пока остаются элементы:
        while (!list.isEmpty()) {
            // Удаление элемента и вывод его
            var aLink = list.deleteFirst();
            System.out.print("Deleted ");
            aLink.displayLink();
            System.out.println();
        }

        // Вывод содержимого списка
        list.displayList();
    }
}

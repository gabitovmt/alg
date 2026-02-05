package alg.ch05.ex02;

// Работа со связанным списком
public class LinkListApp {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        var list = new LinkList();
        list.insertFirst(22, 2.99);
        list.insertFirst(44, 4.99);
        list.insertFirst(66, 6.99);
        list.insertFirst(88, 8.99);
        list.displayList();

        // Поиск элемента
        Link f = list.find(44);
        if (f != null) {
            System.out.println("Found link with key " + f.getIData());
        } else {
            System.out.println("Can't find link");
        }

        // Удаление элемента
        Link d = list.delete(66);
        if (d != null) {
            System.out.println("Deleted link with key " + d.getIData());
        } else {
            System.out.println("Can't delete link");
        }

        list.displayList();
    }
}

package alg.ch05.ex03;

// Двусторонний список
public class FirstLastList {
    // Ссылки на первый и последний элементы
    private Link first;
    private Link last;

    public boolean isEmpty() {
        return first == null;
    }

    // Вставка элемента в начало списка
    public void insertFirst(long dData) {
        // Создание нового элемента
        var newLink = new Link(dData);
        if (isEmpty()) {
            // Список пуст. newLink <-- last
            last = newLink;
        }
        // newLink --> старое значение first
        newLink.next = first;
        // first --> newLink
        first = newLink;
    }

    // Вставка элемента в конец списка
    public void insertLast(long dData) {
        // Создание нового элемента
        var newLink = new Link(dData);
        if (isEmpty()) {
            // Список пуст. first --> newLink
            first = newLink;
        } else {
            // Старое значение last --> newLink
            last.next = newLink;
        }
        // newLink <-- last
        last = newLink;
    }

    // Удаление первого элемента списка (предполагается, что список не пуст)
    public long deleteFirst() {
        long temp = first.getDData();
        if (first.next == null) {
            // Если только один элемент. null <-- last
            last = null;
        }
        // first --> старое значение next
        first = first.next;

        return temp;
    }

    @SuppressWarnings("java:S106")
    public void displayList() {
        System.out.print("List (first-->last): ");
        var current = first;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
        System.out.println();
    }
}

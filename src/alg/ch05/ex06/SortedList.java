package alg.ch05.ex06;

public class SortedList {
    private Link first;

    public boolean isEmpty() {
        return first == null;
    }

    // Вставка в порядке сортировки
    public void insert(long key) {
        // Создание нового элемента
        var newLink = new Link(key);
        // От начала списка
        Link previous = null;
        Link current = first;

        // До конца списка
        while (current != null && key > current.getDData()) {
            // Перейти к следующему элементу
            previous = current;
            current = current.next;
        }

        if (previous == null) {
            // В начале списка. first --> newLink
            first = newLink;
        } else {
            // Не в начале. Старое значение previous --> newLink
            previous.next = newLink;
        }

        // newLink --> старое значение current
        newLink.next = current;
    }

    // Предполагается, что список не пуст
    public Link remove() {
        var temp = first;
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

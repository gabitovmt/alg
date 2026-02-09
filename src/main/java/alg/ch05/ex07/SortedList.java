package alg.ch05.ex07;

public class SortedList {
    private Link first;

    public SortedList(Link[] linkArray) {
        // Инициализация списка. Копирование массива в список
        for (Link link : linkArray) {
            insert(link);
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    // Вставка в порядке сортировки
    public void insert(Link k) {
        Link previous = null;
        Link current = first;

        while (current != null && k.getDData() > current.getDData()) {
            previous = current;
            current = current.next;
        }

        if (previous == null) {
            first = k;
        } else {
            previous.next = k;
        }
        k.next = current;
    }

    public Link remove() {
        var temp = first;
        first = first.next;
        return temp;
    }
}

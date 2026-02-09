package alg.ch05.ex02;

public class LinkList {
    private Link first;

    public void insertFirst(int id, double dd) {
        var newLink = new Link(id, dd);
        newLink.next = first;
        first = newLink;
    }

    // Поиск элемента с заданным ключом (предполагается, что список не пуст)
    public Link find(int key) {
        // Поиск начиная с first
        var current = first;

        // Пока совпадение не найдено
        while (current.getIData() != key) {
            if (current.next == null) {
                // Достигнут конец списка и совпадение не найдено
                return null;
            }
            // Остались ещё элементы. Перейти к следующему элементу
            current = current.next;
        }

        // Совпадение обнаружено
        return current;
    }

    // Удаление элемента с заданным ключом (предполагается, что список не пуст)
    public Link delete(int key) {
        // Поиск элемента
        var current = first;
        var previous = first;

        while (current.getIData() != key) {
            if (current.next == null) {
                // Элемент не найден
                return null;
            }
            // Перейти к следующему элементу
            previous = current;
            current = current.next;
        }

        // Совпадение найдено
        if (current == first) {
            // Первый элемент. Изменить first
            first = first.next;
        } else {
            // Не первый элемент. Обойти его в списке
            previous.next = current.next;
        }

        return current;
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

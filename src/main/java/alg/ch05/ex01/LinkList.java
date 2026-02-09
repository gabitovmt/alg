package alg.ch05.ex01;

public class LinkList {
    // Ссылка на первый элемент списка
    private Link first;

    public boolean isEmpty() {
        return first == null;
    }

    // Вставка элемента в начало списка
    public void insertFirst(int id, double dd) {
        var newLink = new Link(id, dd);
        newLink.next = first;   // newLink --> старое значение first
        first = newLink;        // first --> newLink
    }

    // Удаление первого элемента (предполагается, что список не пуст)
    public Link deleteFirst() {
        var temp = first;   // Сохранение ссылки
        first = first.next; // Удаление: first --> ссылка на второй элемент
        return temp;        // Возвращение удалённого элемента
    }

    @SuppressWarnings("java:S106")
    public void displayList() {
        System.out.print("List (first-->last): ");
        var current = first;        // От начала списка
        while (current != null) {   // Перемещение до конца списка
            current.displayLink();  // Вывод данных
            current = current.next; // Переход к следующему элементу
        }
        System.out.println();
    }
}

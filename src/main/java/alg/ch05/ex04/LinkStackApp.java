package alg.ch05.ex04;

// Реализация стека на базе связанного списка
public class LinkStackApp {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        var stack = new LinkStack();
        stack.push(20);
        stack.push(40);
        System.out.println(stack);

        stack.push(60);
        stack.push(80);
        System.out.println(stack);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack);
    }
}

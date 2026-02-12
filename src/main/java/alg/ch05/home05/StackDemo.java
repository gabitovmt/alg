package alg.ch05.home05;

// Программный проект 5.4
// Реализация стека на базе циклического связного списка
public class StackDemo {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        var stack = new Stack<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack);
    }
}

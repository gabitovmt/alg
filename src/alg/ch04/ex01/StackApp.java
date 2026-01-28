package alg.ch04.ex01;

// Работа со стеком
public class StackApp {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        var stack = new StackX(10);
        stack.push(20);
        stack.push(40);
        stack.push(60);
        stack.push(80);

        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }
}

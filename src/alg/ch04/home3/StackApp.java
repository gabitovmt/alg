package alg.ch04.home3;

// Упражнение 3
@SuppressWarnings("java:S106")
public class StackApp {

    public static void main(String[] args) {
        var stack = new Stack(10);
        stack.display();
        stack.push(20);
        stack.push(40);
        stack.push(60);
        stack.push(80);
        stack.display();

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
            stack.display();
        }
    }
}

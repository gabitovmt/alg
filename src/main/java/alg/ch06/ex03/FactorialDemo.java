package alg.ch06.ex03;

public class FactorialDemo {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            System.out.printf("Factorial %2d! %d%n", i, factorial(i));
        }
    }

    private static int factorial(int n) {
        return n == 0 ? 1 : n * factorial(n - 1);
    }
}

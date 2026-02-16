package alg.ch06.ex01;

public class TriangleDemo {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            System.out.printf("Triangle %3s: %2d %d%n", "#" + i, triangle(i), triangleR(i));
        }
    }

    // Вычисление треугольного числа с помощью цикла
    private static int triangle(int n) {
        int total = 0;

        while (n > 0) {
            total += n;
            --n;
        }

        return total;
    }

    // Вычисление треугольного числа с помощью рекурсии
    private static int triangleR(int n) {
        return n == 1 ? 1 : n + triangleR(n - 1);
    }
}

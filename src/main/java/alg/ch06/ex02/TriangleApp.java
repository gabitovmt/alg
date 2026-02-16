package alg.ch06.ex02;

import java.util.Scanner;

// Вычисление треугольных чисел
public class TriangleApp {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        System.out.print("Enter a number: ");
        var in = new Scanner(System.in);
        int number = in.nextInt();

        int answer = triangle(number);

        System.out.println("Triangle=" + answer);
    }

    private static int triangle(int n) {
        return n == 1 ? 1 : n + triangle(n - 1);
    }
}

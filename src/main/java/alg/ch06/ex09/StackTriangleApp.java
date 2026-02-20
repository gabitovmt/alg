package alg.ch06.ex09;

import java.util.Scanner;

public class StackTriangleApp {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        System.out.print("Enter a number: ");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        int answer = new Triangle().recTriangle(num);

        System.out.println("Triangle : " + answer);
    }
}

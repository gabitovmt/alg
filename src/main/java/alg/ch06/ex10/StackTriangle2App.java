package alg.ch06.ex10;

import java.util.Scanner;

public class StackTriangle2App {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        System.out.print("Enter a number: ");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        int answer = new Triangle().stackTriangle(num);

        System.out.println("Triangle : " + answer);
    }
}

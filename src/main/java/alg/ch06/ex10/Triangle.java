package alg.ch06.ex10;

import alg.ch06.ex09.Stack;

// Вычисление треугольных чисел (стек вместо рекурсии)
public class Triangle {
    private final Stack<Integer> stack = new Stack<>();

    public int stackTriangle(int number) {
        int answer = 0;

        while (number > 0) {
            stack.push(number);
            --number;
        }
        while (!stack.isEmpty()) {
            answer += stack.pop();
        }

        return answer;
    }
}

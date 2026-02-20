package alg.ch06.ex09;

// Вычисление треугольных чисел с заменой рекурсии стеком
public class Triangle {
    private int number;
    private int answer;
    private Stack<Params> stack;
    private int codePart;
    private Params params;

    private record Params(int n, int returnAddress) {
    }

    public int recTriangle(int num) {
        stack = new Stack<>();
        number = num;
        codePart = 1;
        while (!step()) {
            // Вызывать, пока step() не вернёт true
        }
        return answer;
    }

    private boolean step() {
        switch (codePart) {
            case 1 -> {
                // Исходный вызов
                params = new Params(number, 6);
                stack.push(params);
                codePart = 2;
            }
            case 2 -> {
                // Вход в метод
                params = stack.peek();
                // Проверка
                if (params.n() == 1) {
                    answer = 1;
                    // Выход
                    codePart = 5;
                } else {
                    // Рекурсивный вызов
                    codePart = 3;
                }
            }
            case 3 -> {
                // Вызов метода
                stack.push(new Params(params.n() - 1, 4));
                // Вход в метод
                codePart = 2;
            }
            case 4 -> {
                // Вычисление
                params = stack.peek();
                answer = answer + params.n();
                codePart = 5;
            }
            case 5 -> {
                // Выход из метода
                params = stack.peek();
                codePart = params.returnAddress();  // 4 или 6
                stack.pop();
            }
            case 6 -> {
                return true;
            }
            default -> throw new IllegalStateException("Unexpected codePart: " + codePart);
        }

        return false;
    }
}

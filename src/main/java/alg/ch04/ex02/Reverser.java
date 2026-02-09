package alg.ch04.ex02;

public class Reverser {
    // Входная строка
    private final String input;

    public Reverser(String in) {
        input = in;
    }

    // Перестановка символов
    public String doRev() {
        var stack = new Stack<Character>(input.length());

        for (int j = 0; j < input.length(); j++) {
            // Чтение символа из входного потока и занесение его в стек
            char ch = input.charAt(j);
            stack.push(ch);
        }

        // Использую StringBuilder, так как он больше подходит для модификации данных
        var output = new StringBuilder();
        while (!stack.isEmpty()) {
            // Извлечение символа из стека
            char ch = stack.pop();
            output.append(ch);
        }

        return output.toString();
    }
}

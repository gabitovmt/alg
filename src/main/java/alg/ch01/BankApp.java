package alg.ch01;

public class BankApp {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        // Создание счёта
        var ba1 = new BankAccount(100.00);

        System.out.print("Before transactions, ");
        // Вывод баланса
        ba1.display();

        // Внесение средств
        ba1.deposit(74.35);
        // Снятие средств
        ba1.withdraw(20.00);

        System.out.print("After transactions, ");
        // Вывод баланса
        ba1.display();

        // Before transactions, balance=100.0
        // After transactions, balance=154.35
    }
}

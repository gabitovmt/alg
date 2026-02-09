package alg.ch01;

public class BankAccount {
    // Баланс счёта
    private double balance;

    // Конструктор
    public BankAccount(double openingBalance) {
        balance = openingBalance;
    }

    // Внесение средств
    public void deposit(double amount) {
        balance += amount;
    }

    // Снятие средств
    public void withdraw(double amount) {
        balance -= amount;
    }

    // Вывод баланса
    @SuppressWarnings("java:S106")
    public void display() {
        System.out.println("balance=" + balance);
    }
}

package alg.ch05.home01;

import alg.ch05.ext01.LinkedList;
import alg.ch05.ext01.TwoEndedList;

public class App {

    @SuppressWarnings("all")
    public static void main(String[] args) {
        LinkedList<Long> list = new TwoEndedList<>();
        for (int i = 0; ; i++) {
            if (i % 1_000_000 == 0) {
                System.out.println(i);
            }
            list.insertFirst((long) i);
        }
        // Создалось 356 млн. записей. Процесс занял 17 Гб. Дальше вставка элементов заметно замедлилось.
    }
}

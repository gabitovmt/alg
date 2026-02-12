package alg.ch05.home06;

// Программный проект 5.5
// Задача Иосифа Флавия
public class TaskDemo {

    public static void main(String[] args) {
        harakiri(7, 3, 1, true);
        iosifFlaviya();
    }

    public static int harakiri(int peopleCount, int count, int startIndex, boolean logged) {
        return new Harakiri(peopleCount, count, startIndex, logged).alive();
    }

    @SuppressWarnings("java:S106")
    public static void iosifFlaviya() {
        System.out.println("\nTask Iosif Flaviya");
        int peopleCount = 20;
        int iosifIndex = 7;

        for (int i = 1; i < 100; i++) {
            if (harakiri(peopleCount, i, 1, false) == iosifIndex) {
                System.out.println("Count: " + i);
                harakiri(peopleCount, i, 1, true);
                return;
            }
        }
        System.out.println("Not found");
    }
}

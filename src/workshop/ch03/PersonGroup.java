package workshop.ch03;

import java.util.List;

public interface PersonGroup {
    Person person(int index);
    int length();

    int swaps();
    int comps();
    int inner();
    int outer();
    boolean doneFlag();
    BarMode barMode();
    List<ArrowText> arrowTexts();

    void createPeople(int size, Order order);
    void sortStep();
}

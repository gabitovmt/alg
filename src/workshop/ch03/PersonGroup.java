package workshop.ch03;

import java.util.List;

public interface PersonGroup {
    Person person(int index);
    Person tempPerson();
    int size();

    boolean doneFlag();
    BarMode barMode();
    List<String> statusTexts();
    List<ArrowText> arrowTexts();

    void createPeople(int size, Order order);
    void sortStep();
}

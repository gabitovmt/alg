package workshop.ch04;

public interface PersonGroup extends Stack<Integer> {
    int DEFAULT_CAPACITY = 10;
    int DEFAULT_SIZE = 4;
    int MAX_HEIGHT = 999;
    String DEFAULT_NOTE = "Press any button";

    int getCapacity();

    int getSize();
    void setSize(int size);

    int getCurrentPosition();

    Person getPerson(int index);
    Person getLastPerson();
    void setLastPerson(Person person);

    String getArrowText();

    String getNote();
    void setNote(String note);
    void setDefaultNote();

    void reset();
}

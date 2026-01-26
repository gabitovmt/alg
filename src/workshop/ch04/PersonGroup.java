package workshop.ch04;

public interface PersonGroup extends Stack<Integer> {
    int getCapacity();
    int getCurrentPosition();
    Person getPerson(int index);
    String getArrowText();
    String getNote();
}

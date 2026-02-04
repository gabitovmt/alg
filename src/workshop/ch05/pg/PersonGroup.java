package workshop.ch05.pg;

public interface PersonGroup {
    String getNote();

    int size();
    Person getPerson(int index);

    Person insertingPerson();
    Integer insertingIndex();
    Integer deletingIndex();
    Integer currentIndex();
}

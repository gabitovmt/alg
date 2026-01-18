package workshop.ch02;

public interface PersonGroup {
    Person[] getPersons();
    void setPersons(Person[] persons);

    Person getPerson(int index);
    Person getCurrentPerson();
    void setPerson(int index, Person person);
    void setCurrentPerson(Person person);

    int getCapacity();
    int getSize();
    void setSize(int size);

    String getNote();
    void setNote(String note);
    void setDefaultNote();

    int getPosition();
    int getPrevPosition();
    void resetPosition();
    void setPosition(int position);
    void nextPosition();

    boolean isShowRange();
    void setShowRange(boolean isShowRange);
    int getOldLB();
    void setOldLB(int oldLB);
    int getOldUB();
    void setOldUB(int oldUB);
}

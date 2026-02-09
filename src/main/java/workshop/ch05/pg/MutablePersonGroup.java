package workshop.ch05.pg;

public interface MutablePersonGroup extends PersonGroup {
    void reset();

    void setNote(String note);
    void setDefaultNote();

    void setSize(int size);
    void setPerson(int index, Person person);

    void doFill(int size);

    void setInsertingPerson(Person person);
    void setInsertingIndex(Integer index);
    void setDeletingIndex(Integer index);
    void setCurrentIndex(int index);

    void setSorted(boolean isSorted);
    void setCanChangeSorted(boolean canChange);
}

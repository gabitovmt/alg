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
    int getLowerBound();
    void setLowerBound(int lowerBound);
    int getUpperBound();
    void setUpperBound(int upperBound);
    void resetBounds();

    int getMaxHeight();
    int getMaxSize();

    void doFill(int size);
    int getDuplicate(int value);
    void checkDuplicates();

    void newArray(Integer size);
    void fill(Integer size);
    void insert(Integer key);
    void find(Integer key);
    void delete(Integer key);

    boolean hasDuplicate();
    void setHasDuplicate(boolean hasDuplicate);
    void setCanChangeDuplicate(boolean canChangeDuplicate);

    boolean isLinearSearch();
    void setLinearSearch(boolean isLinearSearch);
    void setCanChangeSearch(boolean canChangeSearch);
}

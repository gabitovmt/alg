package workshop.ch02.array;

import workshop.ch02.PersonGroup;

public interface NonOrderedPersonGroup extends PersonGroup {
    int getMaxHeight();
    int getMaxSize();

    boolean hasDuplicate();
    void setHasDuplicate(boolean hasDuplicate);
    void setCanChangeDuplicate(boolean canChangeDuplicate);

    void doFill(int size);
    void checkDuplicates();

    void newArray(Integer size);
    void fill(Integer size);
    void insert(Integer key);
    void find(Integer key);
    void delete(Integer key);
}

package workshop.ch04.pg;

public interface MutablePersonGroup extends PersonGroup {
    void setSize(int size);
    void setPerson(int idx, Person person);
    void setNote(String note);
    void setDefaultNote();
    void reset();
}

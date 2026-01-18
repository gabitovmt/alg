package workshop.ch02;

public abstract class BasePersonGroup implements PersonGroup {
    protected static final String DEFAULT_NOTE = "Press any button";

    private Person[] persons;
    private int size = 0;

    private String note = DEFAULT_NOTE;

    private int position = 0;
    private int prevPosition = 0;

    private boolean isShowRange = false;
    private int oldLB = 0;
    private int oldUB = 0;

    @Override
    public Person[] getPersons() {
        return persons;
    }

    @Override
    public void setPersons(Person[] persons) {
        this.persons = persons;
    }

    @Override
    public Person getPerson(int index) {
        return persons[index];
    }

    @Override
    public Person getCurrentPerson() {
        return persons[position];
    }

    @Override
    public void setPerson(int index, Person person) {
        persons[index] = person;
    }

    @Override
    public void setCurrentPerson(Person person) {
        persons[position] = person;
    }

    @Override
    public int getCapacity() {
        return persons != null ? persons.length : 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String getNote() {
        return note;
    }

    @Override
    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public void setDefaultNote() {
        setNote(DEFAULT_NOTE);
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public int getPrevPosition() {
        return prevPosition;
    }

    @Override
    public void resetPosition() {
        prevPosition = position = 0;
    }

    @Override
    public void setPosition(int position) {
        this.prevPosition = this.position;
        this.position = position;
    }

    @Override
    public void nextPosition() {
        setPosition(position + 1);
    }

    @Override
    public boolean isShowRange() {
        return isShowRange;
    }

    @Override
    public void setShowRange(boolean isShowRange) {
        this.isShowRange = isShowRange;
    }

    @Override
    public int getOldLB() {
        return oldLB;
    }

    @Override
    public void setOldLB(int oldLB) {
        this.oldLB = oldLB;
    }

    @Override
    public int getOldUB() {
        return oldUB;
    }

    @Override
    public void setOldUB(int oldUB) {
        this.oldUB = oldUB;
    }
}

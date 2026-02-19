package workshop.ch06.mergesort.pg;

public interface PersonGroup {
    Person[] persons();
    void reset();
    boolean isDone();
    void setDone();
    String note();
    void setNote(String note);
    ArrowText[] arrowTexts();
    void switchSize();
    void switchOrder();
    Size size();
    Order order();
    int comparisons();
    int copies();
    void incComparisons();
    void incCopies();
    void sortStep();
    int lower();
    void setLower(int lower);
    int upper();
    void setUpper(int upper);
    int mid();
    void setMid(int mid);
    void setPtr(int ptr);
}

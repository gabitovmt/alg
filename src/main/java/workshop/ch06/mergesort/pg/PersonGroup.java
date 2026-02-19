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
}

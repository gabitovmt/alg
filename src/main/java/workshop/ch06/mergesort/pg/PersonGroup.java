package workshop.ch06.mergesort.pg;

public interface PersonGroup {
    Person[] persons();
    void reset();
    void switchSize();
    void switchOrder();
    Size size();
    Order order();
    int comparisons();
    int copies();
    void incComparisons();
    void incCopies();
}

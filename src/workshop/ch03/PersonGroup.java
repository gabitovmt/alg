package workshop.ch03;

public interface PersonGroup {
    Person person(int index);
    int length();

    int swaps();
    int comps();
    int inner();
    int outer();
    boolean doneFlag();
    BarMode barMode();
}

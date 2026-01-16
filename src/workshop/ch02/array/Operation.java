package workshop.ch02.array;

public interface Operation {
    OperationMode getMode();
    boolean atStart();
    void run(Integer value);
}

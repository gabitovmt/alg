package workshop.ch02;

public interface Operation {
    OperationMode getMode();
    boolean atStart();
    void run(Integer value);
}

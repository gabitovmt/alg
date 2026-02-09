package workshop.ch05.operation;

public interface Operation {
    OperationMode getMode();
    void run(Integer value);
}

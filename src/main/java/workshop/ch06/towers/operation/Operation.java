package workshop.ch06.towers.operation;

public interface Operation {
    OperationMode mode();
    void run(Integer value);
}

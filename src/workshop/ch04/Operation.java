package workshop.ch04;

public interface Operation {
    OperationMode getMode();
    Integer run(Integer value);
}

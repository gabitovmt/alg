package workshop.ch04.operation;

public interface Operation<T extends Enum<T>> {
    T getMode();
    Integer run(Integer value);
}

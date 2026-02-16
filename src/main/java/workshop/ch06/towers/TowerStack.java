package workshop.ch06.towers;

public class TowerStack extends Stack<Tower> {

    public TowerStack(int maxSize) {
        super(Tower.class, maxSize);
    }
}

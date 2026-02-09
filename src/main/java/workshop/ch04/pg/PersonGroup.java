package workshop.ch04.pg;

import java.util.Collection;

public interface PersonGroup {
    int getCapacity();
    int getSize();
    Person getPerson(int index);
    Collection<ArrowText> getArrowTexts();
    String getNote();
}

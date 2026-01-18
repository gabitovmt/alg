package workshop.ch02;

public abstract class BasePersonGroupOperation extends BaseOperation {
    protected final PersonGroup group;
    
    protected BasePersonGroupOperation(OperationMode mode, PersonGroup group) {
        super(mode);
        this.group = group;

        addAction(1, it -> run1());
        addAction(2, this::run2);
        addAction(3, it -> run3());
        addAction(4, it -> run4());
        addAction(5, it -> run5());
        addAction(6, it -> run6());
        addAction(7, it -> run7());
        addAction(8, it -> run8());
        addAction(9, it -> run9());
        addAction(10, it -> run10());
        addAction(11, it -> run11());
        addAction(12, it -> run12());
    }
    
    protected void run1() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    protected void run2(Integer value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    protected void run3() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    protected void run4() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    protected void run5() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    protected void run6() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    protected void run7() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    protected void run8() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    protected void run9() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    protected void run10() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    protected void run11() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    protected void run12() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

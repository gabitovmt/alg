package alg.ch05.ex08;

class Link {
    long dData;
    Link next;
    Link previous;

    Link(long dData) {
        this.dData = dData;
    }

    @Override
    public String toString() {
        return Long.toString(dData);
    }
}

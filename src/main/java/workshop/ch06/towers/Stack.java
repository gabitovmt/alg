package workshop.ch06.towers;

public class Stack {
    private int maxSize;
    private Params[] stackArray;
    private int top;

    public Stack(int var1) {
        this.maxSize = var1;
        this.stackArray = new Params[this.maxSize];
        this.top = -1;
    }

    public void push(Params var1) {
        this.stackArray[++this.top] = var1;
    }

    public Params pop() {
        return this.stackArray[this.top--];
    }

    public Params peek() {
        return this.stackArray[this.top];
    }

    public boolean isEmpty() {
        return this.top == -1;
    }

    public boolean isFull() {
        return this.top == this.maxSize - 1;
    }
}

package workshop.ch04.stack;

import workshop.ch04.Person;
import workshop.ch04.PersonGroup;

import java.awt.*;

public class PersonGroupImpl implements PersonGroup {
    private Person[] stackArray = new Person[11];
    private int nPersons;
    private Person tempPers;
    private String note;
    private int insKey;
    private String returnString;
    private int codePart;
    private int opMode;
    private int curIn;

    public PersonGroupImpl() {
        this.curIn = 0;
        this.nPersons = 0;
        this.codePart = 1;
        this.note = "Press any button";
    }

    public Person makePerson(int var1) {
        int var2 = 100 + (int) (Math.random() * (double) 154.0F);
        int var3 = 100 + (int) (Math.random() * (double) 154.0F);
        int var4 = 100 + (int) (Math.random() * (double) 154.0F);
        Color var5 = new Color(var2, var3, var4);
        return new Person(var1, var5);
    }

    @Override
    public void newStack() {
        if (this.opMode != 1) {
            this.opMode = 1;
            this.codePart = 1;
        }

        switch (this.codePart) {
            case 1:
                this.note = "Will create new, empty stack";
                this.codePart = 2;
                return;
            case 2:
                this.stackArray = new Person[10];

                for (int var1 = 0; var1 < 10; ++var1) {
                    this.stackArray[var1] = null;
                }

                this.nPersons = 0;
                this.curIn = 0;
                this.note = "New stack created";
                this.codePart = 3;
                return;
            case 3:
                this.note = "Press any button";
                this.codePart = 1;
                return;
            default:
        }
    }

    @Override
    public void doFill() {
        for (int var1 = 0; var1 < 10; ++var1) {
            this.stackArray[var1] = null;
        }

        for (int var3 = 0; var3 < 4; ++var3) {
            int var2 = (int) (Math.random() * (double) 999.0F);
            this.tempPers = this.makePerson(var2);
            this.stackArray[var3] = this.tempPers;
        }

        this.nPersons = 4;
        this.curIn = this.nPersons;
    }

    @Override
    public void push(Integer value) {
        if (this.opMode != 2) {
            this.opMode = 2;
            this.codePart = 1;
        }

        switch (this.codePart) {
            case 1:
                this.note = "Enter key of item to push";
                this.codePart = 2;
                break;
            case 2:
                if (value != null && value >= 0 && value <= 999) {
                    if (this.nPersons > 9) {
                        this.note = "CAN'T PUSH: stack is full";
                        this.codePart = 5;
                    } else {
                        this.insKey = value;
                        this.note = "Will push item with key " + this.insKey;
                        this.tempPers = this.makePerson(this.insKey);
                        this.codePart = 3;
                    }
                } else {
                    this.note = "CAN'T PUSH: need key between 0 and " + 999;
                    this.codePart = 1;
                }
                break;
            case 3:
                ++this.nPersons;
                this.curIn = this.nPersons;
                this.note = "Incremented top";
                this.codePart = 4;
                break;
            case 4:
                this.stackArray[this.nPersons - 1] = this.tempPers;
                this.note = "Inserted item with key " + this.insKey + " at top";
                this.codePart = 5;
                break;
            case 5:
                this.note = "Press any button";
                this.codePart = 1;
        }
    }

    @Override
    public Integer pop() {
        if (this.opMode != 3) {
            this.opMode = 3;
            this.codePart = 1;
        }

        switch (this.codePart) {
            case 1:
                if (this.nPersons == 0) {
                    this.note = "CAN'T POP: stack is empty";
                    this.codePart = 4;
                } else {
                    this.note = "Will pop item from top of stack";
                    this.codePart = 2;
                }

                this.returnString = "";
                break;
            case 2:
                this.returnString = String.valueOf(this.stackArray[this.nPersons - 1].height());
                this.stackArray[this.nPersons - 1] = null;
                this.note = "Item removed; value returned in Number";
                this.codePart = 3;
                break;
            case 3:
                --this.nPersons;
                this.curIn = this.nPersons;
                this.note = "Decremented top";
                this.codePart = 4;
                break;
            case 4:
                this.note = "Press any button";
                this.codePart = 1;
        }

        return Integer.valueOf(returnString);
    }

    @Override
    public Integer peek() {
        if (this.opMode != 4) {
            this.opMode = 4;
            this.codePart = 1;
        }

        switch (this.codePart) {
            case 1:
                if (this.nPersons == 0) {
                    this.note = "CAN'T PEEK: stack is empty";
                    this.codePart = 3;
                } else {
                    this.note = "Will peek at item at top of stack";
                    this.codePart = 2;
                }

                this.returnString = "";
                break;
            case 2:
                this.returnString = String.valueOf(this.stackArray[this.nPersons - 1].height());
                this.note = "Value returned in Number";
                this.codePart = 3;
                break;
            case 3:
                this.note = "Press any button";
                this.codePart = 1;
        }

        return Integer.valueOf(returnString);
    }

    @Override
    public int getCapacity() {
        return stackArray.length;
    }

    @Override
    public int getCurrentPosition() {
        return curIn;
    }

    @Override
    public Person getPerson(int index) {
        return stackArray[index];
    }

    @Override
    public String getArrowText() {
        return "Top";
    }

    @Override
    public String getNote() {
        return note;
    }


}

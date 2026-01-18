package workshop.ch02.orderedarray;

import java.awt.Color;
import java.awt.Graphics;

public class PersonGroup {
    private Person[] personArray;
    private int totalCells;
    private int nPersons;
    private Person tempPers;
    private String note;
    private int fillValue;
    private int insKey;
    private int findKey;
    private int delKey;
    private int codePart;
    private int codePart2;
    private int opMode;
    private int curIn;
    private int oldCurIn;
    private int insertPoint;
    private int drawMode;
    private boolean isLinear;
    private boolean isOKChange;
    private boolean noShiftsYet;
    private boolean isShowRange;
    private int lowerBound;
    private int upperBound;
    private int oldLB;
    private int oldUB;

    public PersonGroup(int var1) {
        this.totalCells = var1;
        this.personArray = new Person[this.totalCells];
        this.curIn = this.oldCurIn = 0;
        this.nPersons = 0;
        this.codePart = 1;
        this.codePart2 = 1;
        this.drawMode = 2;
        this.isLinear = true;
        this.isShowRange = false;
        this.note = "Press any button";
    }

    public Person[] getPersons() {
        return personArray;
    }

    public String getNote() {
        return note;
    }

    public int getPos() {
        return curIn;
    }

    public int getPrevPos() {
        return oldCurIn;
    }

    public boolean isShowRange() {
        return isShowRange;
    }

    public int getOldLB() {
        return oldLB;
    }

    public int getOldUB() {
        return oldUB;
    }

    public boolean getSearchType() {
        return this.isLinear;
    }

    public boolean getChangeStatus() {
        return this.isOKChange;
    }

    public void setSearchType(boolean var1) {
        if (this.isOKChange && var1 != this.isLinear) {
            this.isLinear = var1;
        }

        if (!this.isOKChange) {
            this.note = "To change search type, create array with New";
        }

        this.drawMode = 1;
    }

    public Person makePerson(int var1) {
        int var2 = 100 + (int)(Math.random() * (double)154.0F);
        int var3 = 100 + (int)(Math.random() * (double)154.0F);
        int var4 = 100 + (int)(Math.random() * (double)154.0F);
        Color var5 = new Color(var2, var3, var4);
        return new Person(var1, var5);
    }

    public void newArray(boolean var1, int var2) {
        if (this.opMode != 1) {
            this.opMode = 1;
            this.codePart = 1;
        }

        switch (this.codePart) {
            case 1:
                this.note = "Enter size of array to create";
                this.drawMode = 1;
                this.codePart = 2;
                this.oldCurIn = this.curIn;
                this.curIn = 0;
                return;
            case 2:
                if (var1 && var2 >= 0 && var2 <= 60) {
                    this.totalCells = var2;
                    this.note = "Will create empty array with " + this.totalCells + " cells";
                    this.codePart = 3;
                } else {
                    this.note = "ERROR: use size between 0 and " + 60;
                    this.codePart = 1;
                }

                this.drawMode = 1;
                return;
            case 3:
                this.note = "Select Linear or Binary Search";
                this.isOKChange = true;
                this.drawMode = 1;
                this.codePart = 5;
                return;
            case 4:
                if (this.isLinear) {
                    this.note = "Uses linear search";
                } else {
                    this.note = "Uses binary search";
                }

                this.isOKChange = false;
                return;
            case 5:
                this.personArray = new Person[this.totalCells];

                for(int var3 = 0; var3 < this.totalCells; ++var3) {
                    this.personArray[var3] = null;
                }

                this.nPersons = 0;
                this.note = "New array created; total items = " + this.nPersons;
                this.oldCurIn = this.curIn;
                this.curIn = 0;
                this.drawMode = 2;
                this.codePart = 6;
                return;
            case 6:
                this.note = "Press any button";
                this.drawMode = 1;
                this.codePart = 1;
                return;
            default:
        }
    }

    public void fill(boolean var1, int var2) {
        if (this.opMode != 2) {
            this.opMode = 2;
            this.codePart2 = 1;
        }

        switch (this.codePart2) {
            case 1:
                this.note = "Enter number of items to fill in";
                this.drawMode = 1;
                this.codePart2 = 2;
                return;
            case 2:
                if (var1 && var2 >= 0 && var2 <= this.totalCells) {
                    this.fillValue = var2;
                    this.note = "Will fill in " + this.fillValue + " items (may take several seconds)";
                    this.drawMode = 1;
                    this.codePart2 = 4;
                    return;
                }

                this.note = "ERROR: can't fill more than " + this.totalCells + " items";
                this.drawMode = 1;
                this.codePart2 = 1;
                return;
            case 3:
            default:
                return;
            case 4:
                this.nPersons = 0;
                this.doFill(this.fillValue);
                this.opMode = 2;
                this.note = "Fill completed; total items = " + this.nPersons;
                this.oldCurIn = this.curIn;
                this.curIn = 0;
                this.checkDups();
                this.drawMode = 3;
                this.codePart2 = 5;
                return;
            case 5:
                this.note = "Press any button";
                this.drawMode = 1;
                this.codePart2 = 1;
        }
    }

    public void doFill(int var1) {
        for(int var2 = 0; var2 < this.totalCells; ++var2) {
            this.personArray[var2] = null;
        }

        this.oldCurIn = this.curIn;
        this.curIn = 0;
        this.codePart = 1;

        while(this.nPersons < var1) {
            this.insert(true, 1000);
            int var3 = (int)(Math.random() * (double)999.0F);
            this.insert(true, var3);

            while(this.codePart != 1) {
                this.insert(true, 1000);
            }
        }

    }

    public int getDuplicate(int var1) {
        for(int var2 = 0; var2 < this.totalCells; ++var2) {
            if (this.personArray[var2] != null && this.personArray[var2].getHeight() == var1) {
                return var2;
            }
        }

        return -1;
    }

    public void checkDups() {
        for(int var1 = 0; var1 < this.totalCells - 1; ++var1) {
            for(int var2 = var1 + 1; var2 < this.totalCells; ++var2) {
                if (this.personArray[var1] != null && this.personArray[var2] != null && this.personArray[var1].getHeight() == this.personArray[var2].getHeight()) {
                    this.note = "ERROR: " + var1 + " same as " + var2;
                    this.drawMode = 2;
                    return;
                }
            }
        }

    }

    public void insert(boolean var1, int var2) {
        if (this.opMode != 3) {
            this.opMode = 3;
            this.codePart = 1;
        }

        switch (this.codePart) {
            case 1:
                this.oldCurIn = this.curIn;
                this.curIn = 0;
                this.note = "Enter key of item to insert";
                this.drawMode = 1;
                this.codePart = 2;
                return;
            case 2:
                if (var1 && var2 >= 0 && var2 <= 999) {
                    if (this.nPersons >= this.totalCells) {
                        this.note = "CAN'T INSERT: array is full";
                        this.codePart = 7;
                    } else {
                        this.insKey = var2;
                        this.tempPers = this.makePerson(this.insKey);
                        this.note = "Will insert item with key " + this.insKey;
                        if (!this.isLinear) {
                            this.lowerBound = 0;
                            this.upperBound = this.nPersons - 1;
                            this.oldCurIn = this.curIn;
                            this.curIn = (this.upperBound - this.lowerBound) / 2;
                        }

                        this.codePart = 3;
                    }
                } else {
                    this.note = "CAN'T INSERT: need key between 0 and " + 999;
                    this.codePart = 1;
                }

                this.drawMode = 1;
                return;
            case 3:
                if (this.curIn >= this.nPersons) {
                    this.personArray[this.curIn] = this.tempPers;
                    ++this.nPersons;
                    this.note = "Inserted item " + this.insKey + " at index " + this.curIn;
                    this.codePart = 6;
                } else if (this.isLinear) {
                    if (this.personArray[this.curIn].getHeight() == this.insKey) {
                        this.note = "CAN'T INSERT: duplicate at index " + this.curIn;
                        this.codePart = 7;
                    } else if (this.personArray[this.curIn].getHeight() > this.insKey) {
                        this.note = "Will insert at index " + this.curIn + ", following shift";
                        this.noShiftsYet = true;
                        this.insertPoint = this.curIn;
                        this.codePart = 4;
                    } else {
                        this.oldCurIn = this.curIn++;
                        this.note = "Checking item at index = " + this.curIn;
                        this.codePart = 3;
                    }
                } else {
                    this.isShowRange = true;
                    if (this.personArray[this.curIn].getHeight() == this.insKey) {
                        this.note = "CAN'T INSERT: duplicate at " + this.curIn;
                        this.oldLB = this.oldUB = -1;
                        this.codePart = 7;
                    } else if (this.lowerBound > this.upperBound) {
                        if (this.personArray[this.curIn].getHeight() < this.insKey) {
                            this.oldCurIn = this.curIn++;
                        }

                        this.note = "Will insert at index " + this.curIn + ", following shift";
                        this.noShiftsYet = true;
                        this.insertPoint = this.curIn;
                        this.oldLB = this.oldUB = -1;
                        this.codePart = 4;
                    } else {
                        this.oldLB = this.lowerBound;
                        this.oldUB = this.upperBound;
                        this.oldCurIn = this.curIn;
                        this.curIn = this.lowerBound + (this.upperBound - this.lowerBound) / 2;
                        this.note = "Checking index " + this.curIn + ", range = " + this.lowerBound + " to " + this.upperBound;
                        if (this.personArray[this.curIn].getHeight() < this.insKey) {
                            this.lowerBound = this.curIn + 1;
                        } else {
                            this.upperBound = this.curIn - 1;
                        }

                        this.codePart = 3;
                    }
                }

                this.drawMode = 1;
                return;
            case 4:
                if (this.noShiftsYet) {
                    this.isShowRange = false;
                    this.noShiftsYet = false;
                    this.note = "Will shift cells to make room";
                    this.oldCurIn = this.curIn;
                    this.curIn = this.nPersons;
                    this.codePart = 4;
                } else if (this.curIn == this.insertPoint) {
                    this.personArray[this.curIn] = this.tempPers;
                    ++this.nPersons;
                    this.note = "Have inserted item " + this.insKey + " at index " + this.curIn;
                    this.codePart = 6;
                } else {
                    this.personArray[this.curIn] = this.personArray[this.curIn - 1];
                    this.personArray[this.curIn - 1] = null;
                    this.oldCurIn = this.curIn--;
                    this.note = "Shifted item from index " + this.curIn;
                    this.codePart = 4;
                }

                this.drawMode = 1;
                return;
            case 5:
            default:
                return;
            case 6:
                this.note = "Insertion completed; total items = " + this.nPersons;
                this.drawMode = 1;
                this.codePart = 7;
                return;
            case 7:
                this.oldCurIn = this.curIn;
                this.curIn = 0;
                this.note = "Press any button";
                this.drawMode = 1;
                this.codePart = 1;
        }
    }

    public void find(boolean var1, int var2) {
        if (this.opMode != 4) {
            this.opMode = 4;
            this.codePart = 1;
        }

        switch (this.codePart) {
            case 1:
                this.oldCurIn = this.curIn;
                this.curIn = 0;
                this.note = "Enter key of item to find";
                this.codePart = 2;
                break;
            case 2:
                if (var1 && var2 >= 0 && var2 <= 999) {
                    this.findKey = var2;
                    this.note = "Looking for item with key " + this.findKey;
                    if (!this.isLinear) {
                        this.lowerBound = 0;
                        this.upperBound = this.nPersons - 1;
                        this.oldCurIn = this.curIn;
                        this.curIn = (this.upperBound - this.lowerBound) / 2;
                    }

                    this.codePart = 3;
                } else {
                    this.note = "ERROR: use key between 0 and " + 999;
                    this.codePart = 1;
                }
                break;
            case 3:
                if (this.isLinear) {
                    if (this.curIn < this.nPersons && this.personArray[this.curIn].getHeight() <= this.findKey) {
                        if (this.personArray[this.curIn].getHeight() == this.findKey) {
                            this.note = "Have found item with key " + this.findKey;
                            this.codePart = 6;
                        } else {
                            this.oldCurIn = this.curIn++;
                            this.note = "Checking next cell; index = " + this.curIn;
                            this.codePart = 3;
                        }
                    } else {
                        this.note = "Can't locate item with key " + this.findKey;
                        this.codePart = 6;
                    }
                } else {
                    this.isShowRange = true;
                    if (this.personArray[this.curIn].getHeight() == this.findKey) {
                        this.note = "Have found item with key " + this.findKey;
                        this.oldLB = this.oldUB = -1;
                        this.codePart = 6;
                    } else if (this.lowerBound > this.upperBound) {
                        this.note = "Can't locate item with key " + this.findKey;
                        this.oldLB = this.oldUB = -1;
                        this.codePart = 6;
                    } else {
                        this.oldLB = this.lowerBound;
                        this.oldUB = this.upperBound;
                        this.oldCurIn = this.curIn;
                        this.curIn = this.lowerBound + (this.upperBound - this.lowerBound) / 2;
                        this.note = "Checking index " + this.curIn + ", range = " + this.lowerBound + " to " + this.upperBound;
                        if (this.personArray[this.curIn].getHeight() < this.findKey) {
                            this.lowerBound = this.curIn + 1;
                        } else {
                            this.upperBound = this.curIn - 1;
                        }

                        this.codePart = 3;
                    }
                }
            case 4:
            case 5:
            default:
                break;
            case 6:
                this.oldCurIn = this.curIn;
                this.curIn = 0;
                this.isShowRange = false;
                this.note = "Press any button";
                this.codePart = 1;
        }

        this.drawMode = 1;
    }

    public void delete(boolean var1, int var2) {
        if (this.opMode != 5) {
            this.opMode = 5;
            this.codePart = 1;
        }

        switch (this.codePart) {
            case 1:
                this.oldCurIn = this.curIn;
                this.curIn = 0;
                this.note = "Enter key of item to delete";
                this.codePart = 2;
                break;
            case 2:
                if (var1 && var2 >= 0 && var2 <= 999) {
                    if (!this.isLinear) {
                        this.lowerBound = 0;
                        this.upperBound = this.nPersons - 1;
                        this.oldCurIn = this.curIn;
                        this.curIn = (this.upperBound - this.lowerBound) / 2;
                    }

                    this.delKey = var2;
                    this.note = "Looking for item with key " + this.delKey;
                    this.codePart = 3;
                } else {
                    this.note = "ERROR: use key between 0 and " + 999;
                    this.codePart = 1;
                }
                break;
            case 3:
                if (this.isLinear) {
                    if (this.curIn < this.nPersons && this.personArray[this.curIn].getHeight() <= this.delKey) {
                        if (this.personArray[this.curIn].getHeight() == this.delKey) {
                            this.personArray[this.curIn] = null;
                            this.note = "Have found and deleted item with key " + this.delKey;
                            this.codePart = 4;
                        } else {
                            this.oldCurIn = this.curIn++;
                            this.note = "Checking index = " + this.curIn + " for item";
                            this.codePart = 3;
                        }
                    } else {
                        this.note = "No item with key " + this.delKey + " found";
                        this.codePart = 5;
                    }
                } else {
                    this.isShowRange = true;
                    if (this.personArray[this.curIn].getHeight() == this.delKey) {
                        this.personArray[this.curIn] = null;
                        this.note = "Have found and deleted item with key " + this.delKey;
                        this.oldLB = this.oldUB = -1;
                        this.codePart = 4;
                    } else if (this.lowerBound > this.upperBound) {
                        this.note = "No item with key " + this.delKey + " found";
                        this.codePart = 5;
                        this.oldLB = this.oldUB = -1;
                        this.codePart = 5;
                    } else {
                        this.oldLB = this.lowerBound;
                        this.oldUB = this.upperBound;
                        this.oldCurIn = this.curIn;
                        this.curIn = this.lowerBound + (this.upperBound - this.lowerBound) / 2;
                        this.note = "Checking index " + this.curIn + ", range = " + this.lowerBound + " to " + this.upperBound;
                        if (this.personArray[this.curIn].getHeight() < this.delKey) {
                            this.lowerBound = this.curIn + 1;
                        } else {
                            this.upperBound = this.curIn - 1;
                        }

                        this.codePart = 3;
                    }
                }
                break;
            case 4:
                if (this.curIn < this.nPersons - 1) {
                    this.oldCurIn = this.curIn++;
                    this.personArray[this.curIn - 1] = this.personArray[this.curIn];
                    this.personArray[this.curIn] = null;
                    this.note = "Shifted item from " + this.curIn + " to " + (this.curIn - 1);
                    this.codePart = 4;
                } else {
                    --this.nPersons;
                    this.note = "Shifting completed. Total items = " + this.nPersons;
                    this.oldCurIn = this.curIn;
                    this.curIn = this.nPersons - 1;
                    this.codePart = 6;
                }
                break;
            case 5:
                this.note = "Deletion not completed";
                this.codePart = 6;
                break;
            case 6:
                this.oldCurIn = this.curIn;
                this.curIn = 0;
                this.note = "Press any button";
                this.codePart = 1;
        }

        this.drawMode = 1;
    }
}

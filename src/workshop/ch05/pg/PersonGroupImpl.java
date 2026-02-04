package workshop.ch05.pg;

import java.awt.*;

public class PersonGroupImpl implements PersonGroup {
    private Link[] linkArray = new Link[28];
    private int totalLinks = 0;
    private Person tempPers;
    private String note;
    private int insKey;
    private int findKey;
    private int delKey;
    private int codePart;
    private int codePart2;
    private int opMode;
    private int curIn;
    private boolean notSorted;
    private boolean isOKChangeSort;
    private int insDex;
    private boolean areInserting;
    private boolean insertAtEnd;
    private int delDex;
    private boolean areDeleting;

    public PersonGroupImpl() {
        this.curIn = 0;
        this.codePart = 1;
        this.codePart2 = 1;
        this.note = "Press any button";
        this.notSorted = true;
        this.isOKChangeSort = false;
        this.areInserting = false;
    }

    private Person makePerson(int var1) {
        int var2 = 100 + (int) (Math.random() * (double) 154.0F);
        int var3 = 100 + (int) (Math.random() * (double) 154.0F);
        int var4 = 100 + (int) (Math.random() * (double) 154.0F);
        Color var5 = new Color(var2, var3, var4);
        return new Person(var1, var5);
    }

    public boolean getSortStatus() {
        return this.notSorted;
    }

    public boolean getChangeStatus() {
        return this.isOKChangeSort;
    }

    public void setSortStatus(boolean var1) {
        if (this.isOKChangeSort && var1 != this.notSorted) {
            this.notSorted = var1;
        }

        if (!this.isOKChangeSort) {
            this.note = "To change sort status, create list with New";
        }
    }

    public void newList(Integer var2) {
        this.areInserting = false;
        this.areDeleting = false;
        if (this.opMode != 1) {
            this.opMode = 1;
            this.codePart = 1;
        }

        switch (this.codePart) {
            case 1:
                this.note = "Enter size of linked list to create";
                this.codePart = 2;
                this.curIn = 0;
                return;
            case 2:
                if (var2 != null && var2 >= 0 && var2 <= 28) {
                    this.note = "Will create list with " + var2 + " links";
                    this.codePart = 3;
                } else {
                    this.note = "ERROR: use size between 0 and " + 28;
                    this.codePart = 1;
                }
                return;
            case 3:
                this.note = "Select unsorted or sorted data";
                this.isOKChangeSort = true;
                this.codePart = 4;
                return;
            case 4:
                if (this.notSorted) {
                    this.note = "Data will not be sorted";
                } else {
                    this.note = "Data will be sorted";
                }

                this.isOKChangeSort = false;
                this.totalLinks = 0;
                this.codePart = 5;
                return;
            case 5:
                this.totalLinks = var2;
                this.doFill(this.totalLinks);
                this.note = "New list created; total links = " + this.totalLinks;
                this.curIn = 0;
                this.codePart = 6;
                return;
            case 6:
                this.note = "Press any button";
                this.codePart = 1;
                return;
            default:
        }
    }

    public void doFill(int var1) {
        this.totalLinks = var1;

        for (int var2 = 0; var2 < 28; ++var2) {
            this.linkArray[var2] = null;
        }

        this.curIn = 0;
        this.codePart = 1;
        if (this.notSorted) {
            for (int var8 = 0; var8 < this.totalLinks; ++var8) {
                int var9 = (int) (Math.random() * (double) 999.0F);
                this.tempPers = this.makePerson(var9);
                this.linkArray[var8] = new Link(this.tempPers);
            }

        } else {
            int var4 = 0;
            int var6 = 0;

            for (int var7 = 0; var7 < this.totalLinks; ++var7) {
                int var5 = (int) ((999.0F - (float) var6) / ((float) this.totalLinks - (float) var7));
                int var3 = (int) (Math.random() * (double) var5);
                var4 += var3;
                var6 = var4;
                this.tempPers = this.makePerson(var4);
                this.linkArray[var7] = new Link(this.tempPers);
            }

        }
    }

    public void insert(Integer var2) {
        this.areDeleting = false;
        if (this.opMode != 3) {
            this.opMode = 3;
            this.codePart = 1;
        }

        switch (this.codePart) {
            case 1:
                this.curIn = 0;
                this.insertAtEnd = false;
                this.note = "Enter key of item to insert";
                this.codePart = 2;
                return;
            case 2:
                if (var2 != null && var2 >= 0 && var2 <= 999) {
                    if (this.totalLinks >= 28) {
                        this.note = "CAN'T INSERT: no room in display";
                        this.codePart = 6;
                    } else {
                        this.insKey = var2;
                        this.tempPers = this.makePerson(this.insKey);
                        if (this.notSorted) {
                            this.note = "Will insert item with key " + this.insKey;
                            this.codePart = 4;
                        } else {
                            this.note = "Will search for insertion point";
                            this.codePart = 3;
                        }
                    }
                } else {
                    this.note = "CAN'T INSERT: need key between 0 and " + 999;
                    this.codePart = 1;
                }
                return;
            case 3:
                if (this.curIn == this.totalLinks - 1 && this.insKey > this.linkArray[this.curIn].item().height()) {
                    this.note = "Found insertion point at end of list";
                    this.insertAtEnd = true;
                    this.codePart = 5;
                } else if (this.insKey > this.linkArray[this.curIn].item().height()) {
                    this.note = "Searching for insertion point";
                    this.curIn++;
                    this.codePart = 3;
                } else {
                    this.note = "Have found insertion point";
                    this.codePart = 4;
                }
                return;
            case 4:
                this.areInserting = true;
                if (this.notSorted) {
                    this.insDex = 0;
                } else {
                    this.insDex = this.curIn;
                }

                this.note = "Inserted item; will redraw list";
                this.codePart = 5;
                return;
            case 5:
                if (this.insertAtEnd) {
                    this.curIn++;
                    this.note = "Inserted item with key " + this.insKey + " at end of list";
                } else {
                    this.areInserting = false;

                    for (int var3 = this.totalLinks; var3 > this.curIn; --var3) {
                        this.linkArray[var3] = this.linkArray[var3 - 1];
                    }

                    this.note = "Inserted item with key " + this.insKey;
                }

                this.linkArray[this.curIn] = new Link(this.tempPers);
                ++this.totalLinks;
                this.codePart = 6;
                return;
            case 6:
                this.note = "Insertion completed; total items = " + this.totalLinks;
                this.codePart = 7;
                return;
            case 7:
                this.curIn = 0;
                this.note = "Press any button";
                this.codePart = 1;
                return;
            default:
        }
    }

    public void find(Integer var2) {
        this.areInserting = false;
        this.areDeleting = false;
        if (this.opMode != 4) {
            this.opMode = 4;
            this.codePart = 1;
        }

        switch (this.codePart) {
            case 1:
                this.note = "Enter key of item to find";
                this.codePart = 2;
                break;
            case 2:
                if (var2 != null && var2 >= 0 && var2 <= 999) {
                    this.findKey = var2;
                    this.curIn = 0;
                    this.note = "Looking for item with key " + this.findKey;
                    this.codePart = 3;
                } else {
                    this.note = "ERROR: use key between 0 and " + 999;
                    this.codePart = 1;
                }
                break;
            case 3:
                if (this.linkArray[this.curIn].item().height() == this.findKey) {
                    this.note = "Have found item with key " + this.findKey;
                    this.codePart = 6;
                } else if (this.curIn != this.totalLinks - 1 && (this.notSorted || this.linkArray[this.curIn].item().height() <= this.findKey)) {
                    this.note = "Searching for item with key " + this.findKey;
                    this.curIn++;
                    this.codePart = 3;
                } else {
                    this.note = "Can't locate item with key " + this.findKey;
                    this.codePart = 6;
                }
            case 4:
            case 5:
            default:
                break;
            case 6:
                this.curIn = 0;
                this.note = "Press any button";
                this.codePart = 1;
        }
    }

    public void delete(Integer var2) {
        this.areInserting = false;
        if (this.opMode != 5) {
            this.opMode = 5;
            this.codePart = 1;
        }

        switch (this.codePart) {
            case 1:
                this.note = "Enter key of item to delete";
                this.codePart = 2;
                return;
            case 2:
                if (var2 != null && var2 >= 0 && var2 <= 999) {
                    this.delKey = var2;
                    this.curIn = 0;
                    this.note = "Looking for item with key " + this.delKey;
                    this.codePart = 3;
                } else {
                    this.note = "ERROR: use key between 0 and " + 999;
                    this.codePart = 1;
                }
                return;
            case 3:
                if (this.linkArray[this.curIn].item().height() == this.delKey) {
                    this.note = "Have found item with key " + this.delKey;
                    if (this.curIn == this.totalLinks - 1) {
                        this.codePart = 5;
                    } else {
                        this.codePart = 4;
                    }
                } else if (this.curIn != this.totalLinks - 1 && (this.notSorted || this.linkArray[this.curIn].item().height() <= this.delKey)) {
                    this.note = "Searching for item with key " + this.delKey;
                    this.curIn++;
                    this.codePart = 3;
                } else {
                    this.note = "Can't locate item with key " + this.delKey;
                    this.codePart = 6;
                }

                return;
            case 4:
                this.areDeleting = true;
                this.delDex = this.curIn;
                this.note = "Deleted item; will redraw list";
                this.codePart = 5;
                return;
            case 5:
                this.areDeleting = false;

                for (int var3 = this.curIn; var3 < this.totalLinks - 1; ++var3) {
                    this.linkArray[var3] = this.linkArray[var3 + 1];
                }

                --this.totalLinks;
                this.curIn = 0;
                this.note = "Deleted item with key " + this.delKey;
                this.codePart = 6;
                return;
            case 6:
                this.curIn = 0;
                this.note = "Press any button";
                this.codePart = 1;
                return;
            default:
        }
    }

    @Override
    public String getNote() {
        return note;
    }

    @Override
    public int size() {
        return totalLinks;
    }

    @Override
    public Person getPerson(int index) {
        return linkArray[index].item();
    }

    @Override
    public Person insertingPerson() {
        return tempPers;
    }

    @Override
    public Integer insertingIndex() {
        return areInserting ? insDex : null;
    }

    @Override
    public Integer deletingIndex() {
        return areDeleting ? delDex : null;
    }

    @Override
    public Integer currentIndex() {
        return curIn;
    }
}

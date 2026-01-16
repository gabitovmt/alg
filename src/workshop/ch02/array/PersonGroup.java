package workshop.ch02.array;

import java.util.Arrays;

public class PersonGroup {
    private static final String DEFAULT_NOTE = "Press any button";
    private static final int MAX_HEIGHT = 999;

    private Person[] persons;
    private int size;
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
    private int lastDeletion;
    private DrawMode drawMode;
    private boolean dupsOK;
    private boolean isOKChangeDups;
    private boolean wasJustFound;
    private int nDeleted;

    public PersonGroup(int size) {
        this.size = size;
        persons = new Person[size];
        curIn = oldCurIn = 0;
        nPersons = 0;
        codePart = 1;
        codePart2 = 1;
        drawMode = DrawMode.FULL;
        dupsOK = false;
        note = DEFAULT_NOTE;
    }

    public Person[] getPersons() {
        return persons;
    }

    public String getNote() {
        return note;
    }

    public int getCurIn() {
        return curIn;
    }

    public int getOldCurIn() {
        return oldCurIn;
    }

    public DrawMode getDrawMode() {
        return drawMode;
    }

    public void setDrawMode(DrawMode drawMode) {
        this.drawMode = drawMode;
    }

    public boolean getDupsStatus() {
        return dupsOK;
    }

    public boolean getChangeStatus() {
        return isOKChangeDups;
    }

    public void setDupsStatus(boolean var1) {
        if (isOKChangeDups && var1 != dupsOK) {
            dupsOK = var1;
        }

        if (!isOKChangeDups) {
            note = "To change duplication status, create array with New";
        }

        drawMode = DrawMode.SHORT;
    }

    public void newArray(Integer value) {
        if (opMode != 1) {
            opMode = 1;
            codePart = 1;
        }

        switch (codePart) {
            case 1:
                note = "Enter size of array to create";
                drawMode = DrawMode.SHORT;
                codePart = 2;
                oldCurIn = curIn;
                curIn = 0;
                break;
            case 2:
                final int MAX_SIZE = 60;
                if (value != null && value >= 0 && value <= MAX_SIZE) {
                    size = value;
                    note = "Will create empty array with " + size + " cells";
                    codePart = 3;
                } else {
                    note = "ERROR: use size between 0 and " + MAX_SIZE;
                    codePart = 1;
                }

                drawMode = DrawMode.SHORT;
                break;
            case 3:
                note = "Select Duplicates OK, or No Dups";
                isOKChangeDups = true;
                drawMode = DrawMode.SHORT;
                codePart = 5;
                break;
            case 5:
                persons = new Person[size];
                nPersons = 0;
                note = "New array created; total items = " + nPersons;
                isOKChangeDups = false;
                oldCurIn = curIn;
                curIn = 0;
                drawMode = DrawMode.FULL;
                codePart = 6;
                break;
            case 6:
                note = DEFAULT_NOTE;
                drawMode = DrawMode.SHORT;
                codePart = 1;
                break;
            default:
        }
    }

    public void fill(Integer value) {
        if (opMode != 2) {
            opMode = 2;
            codePart2 = 1;
        }

        switch (codePart2) {
            case 1:
                note = "Enter number of items to fill in";
                drawMode = DrawMode.SHORT;
                codePart2 = 2;
                break;
            case 2:
                if (value != null && value >= 0 && value <= persons.length) {
                    fillValue = value;
                    note = "Will fill in " + fillValue + " items";
                    drawMode = DrawMode.SHORT;
                    codePart2 = 3;
                    break;
                }

                note = "ERROR: can't fill more than " + persons.length + " items";
                drawMode = DrawMode.SHORT;
                codePart2 = 1;
                break;
            case 3:
                nPersons = 0;
                doFill(fillValue);
                opMode = 2;
                note = "Fill completed; total items = " + nPersons;
                oldCurIn = curIn;
                curIn = 0;
                if (!dupsOK) {
                    checkDups();
                }
                drawMode = DrawMode.FULL;
                codePart2 = 4;
                break;
            case 4:
                note = DEFAULT_NOTE;
                drawMode = DrawMode.SHORT;
                codePart2 = 1;
                break;
            default:
        }
    }

    public void doFill(int size) {
        Arrays.fill(persons, null);

        oldCurIn = curIn;
        curIn = 0;
        codePart = 1;

        while (nPersons < size) {
            insert(1000);
            int height = Utils.nextHeight();

            if (!dupsOK) {
                while (getDuplicate(height) != -1) {
                    height = Utils.nextHeight();
                }
            }
            insert(height);

            while (codePart != 1) {
                insert(1000);
            }
        }

    }

    public int getDuplicate(int value) {
        for (int idx = 0; idx < persons.length; ++idx) {
            if (persons[idx] != null && persons[idx].getHeight() == value) {
                return idx;
            }
        }

        return -1;
    }

    public void checkDups() {
        for (int i = 0; i < persons.length - 1; ++i) {
            for (int j = i + 1; j < persons.length; ++j) {
                if (persons[i] != null && persons[j] != null
                        && persons[i].getHeight() == persons[j].getHeight()
                ) {
                    note = "ERROR: " + i + " same as " + j;
                    drawMode = DrawMode.FULL;
                    return;
                }
            }
        }
    }

    public void insert(Integer value) {
        if (opMode != 3) {
            opMode = 3;
            codePart = 1;
        }

        switch (codePart) {
            case 1:
                oldCurIn = curIn;
                curIn = 0;
                note = "Enter key of item to insert";
                drawMode = DrawMode.SHORT;
                codePart = 2;
                break;
            case 2:
                if (value != null && value >= 0 && value <= MAX_HEIGHT) {
                    if (nPersons >= persons.length) {
                        note = "CAN'T INSERT: array is full";
                        codePart = 6;
                    } else {
                        insKey = value;
                        tempPers = new Person(insKey);
                        note = "Will insert item with key " + insKey;
                        codePart = 4;
                    }
                } else {
                    note = "CAN'T INSERT: need key between 0 and " + MAX_HEIGHT;
                    codePart = 1;
                }

                drawMode = DrawMode.SHORT;
                break;
            case 4:
                oldCurIn = curIn;
                curIn = nPersons;
                persons[curIn] = tempPers;
                ++nPersons;
                note = "Inserted item with key " + insKey + " at index " + curIn;
                drawMode = DrawMode.SHORT;
                codePart = 5;
                break;
            case 5:
                note = "Insertion completed; total items = " + nPersons;
                if (!dupsOK) {
                    checkDups();
                }
                drawMode = DrawMode.FULL;
                codePart = 6;
                break;
            case 6:
                oldCurIn = curIn;
                curIn = 0;
                note = DEFAULT_NOTE;
                drawMode = DrawMode.SHORT;
                codePart = 1;
                break;
            default:
        }
    }

    public void find(Integer value) {
        if (opMode != 4) {
            opMode = 4;
            codePart = 1;
        }

        switch (codePart) {
            case 1:
                oldCurIn = curIn;
                curIn = 0;
                note = "Enter key of item to find";
                codePart = 2;
                break;
            case 2:
                if (value != null && value >= 0 && value <= MAX_HEIGHT) {
                    findKey = value;
                    note = "Looking for item with key " + findKey;
                    codePart = 3;
                } else {
                    note = "ERROR: use key between 0 and " + MAX_HEIGHT;
                    codePart = 1;
                }
                break;
            case 3:
                if (curIn >= nPersons) {
                    note = "Can't locate item with key " + findKey;
                    codePart = 6;
                } else if (persons[curIn].getHeight() == findKey) {
                    note = "Have found item with key " + findKey;
                    wasJustFound = true;
                    codePart = dupsOK ? 4 : 6;
                } else {
                    oldCurIn = curIn++;
                    note = "Checking next cell; index = " + curIn;
                    codePart = 3;
                }
                break;
            case 4:
                if (wasJustFound) {
                    oldCurIn = curIn++;
                }

                if (curIn >= nPersons) {
                    note = "No additional items with key " + findKey;
                    codePart = 6;
                } else if (persons[curIn].getHeight() == findKey) {
                    note = "Have found additional item with key " + findKey + " at index " + curIn;
                    wasJustFound = true;
                    codePart = 4;
                } else {
                    if (!wasJustFound) {
                        oldCurIn = curIn++;
                    }

                    wasJustFound = false;
                    note = "Checking for additional matches; index = " + curIn;
                    codePart = 4;
                }
                break;
            case 6:
                oldCurIn = curIn;
                curIn = 0;
                note = DEFAULT_NOTE;
                codePart = 1;
                break;
            default:
        }

        drawMode = DrawMode.SHORT;
    }

    public void delete(Integer value) {
        if (opMode != 5) {
            opMode = 5;
            codePart = 1;
        }

        switch (codePart) {
            case 1:
                oldCurIn = curIn;
                curIn = 0;
                lastDeletion = -1;
                nDeleted = 0;
                note = "Enter key of item to delete";
                codePart = 2;
                break;
            case 2:
                if (value != null && value >= 0 && value <= MAX_HEIGHT) {
                    delKey = value;
                    note = "Looking for item with key " + delKey;
                    codePart = 3;
                } else {
                    note = "ERROR: use key between 0 and " + MAX_HEIGHT;
                    codePart = 1;
                }
                break;
            case 3:
                if (curIn >= nPersons) {
                    if (lastDeletion == -1) {
                        note = "No item with key " + delKey + " found";
                        codePart = 5;
                    } else {
                        note = "No additional items with key " + delKey + " found";
                        codePart = 6;
                    }
                } else if (persons[curIn].getHeight() == delKey) {
                    persons[curIn] = null;
                    note = "Have found and deleted item with key " + delKey;
                    lastDeletion = curIn;
                    if (dupsOK) {
                        nDeleted = 1;
                        codePart = 10;
                    } else {
                        codePart = 4;
                    }
                } else {
                    oldCurIn = curIn++;
                    note = "Checking index = " + curIn + " for item";
                    codePart = 3;
                }
                break;
            case 4:
                if (curIn < nPersons - 1) {
                    oldCurIn = curIn++;
                    persons[curIn - 1] = persons[curIn];
                    persons[curIn] = null;
                    note = "Shifted item from " + curIn + " to " + (curIn - 1);
                } else {
                    --nPersons;
                    note = "Shifting completed. Total items = " + nPersons;
                    oldCurIn = curIn;
                    curIn = nPersons - 1;
                    if (dupsOK) {
                        curIn = lastDeletion;
                        codePart = 3;
                    } else {
                        codePart = 6;
                    }
                }
                break;
            case 5:
                note = "Deletion not completed";
                codePart = 6;
                break;
            case 6:
                oldCurIn = curIn;
                curIn = 0;
                note = DEFAULT_NOTE;
                codePart = 1;
                break;
            case 10:
                oldCurIn = curIn;
                curIn += nDeleted;
                note = "Will shift item " + nDeleted + " spaces";
                codePart = 11;
                break;
            case 11:
                if (curIn < nPersons) {
                    persons[curIn - nDeleted] = persons[curIn];
                    persons[curIn] = null;
                    note = "Shifted item from " + curIn + " to " + (curIn - nDeleted);
                    oldCurIn = curIn;
                    curIn -= nDeleted;
                    codePart = 12;
                } else {
                    nPersons -= nDeleted;
                    note = "Shifts complete; no more items to delete";
                    codePart = 6;
                }
                break;
            case 12:
                if (persons[curIn].getHeight() == delKey) {
                    ++nDeleted;
                    persons[curIn] = null;
                    note = "Have deleted additional item with key " + delKey;
                    lastDeletion = curIn;
                    oldCurIn = curIn;
                    curIn += nDeleted;
                } else {
                    note = "Item at " + curIn + " is not a duplicate";
                    oldCurIn = curIn;
                    curIn += nDeleted + 1;
                }

                codePart = 11;
                break;
            default:
        }

        drawMode = DrawMode.SHORT;
    }
}

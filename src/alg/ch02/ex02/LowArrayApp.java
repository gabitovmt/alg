package alg.ch02.ex02;

public class LowArrayApp {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        var arr = new LowArray(100);
        int nElems = 0;

        arr.setElem(0, 77);
        arr.setElem(1, 99);
        arr.setElem(2, 44);
        arr.setElem(3, 55);
        arr.setElem(4, 22);
        arr.setElem(5, 88);
        arr.setElem(6, 11);
        arr.setElem(7, 0);
        arr.setElem(8, 66);
        arr.setElem(9, 33);
        nElems = 10;

        int j;
        for (j = 0; j < nElems; j++) {
            System.out.print(arr.getElem(j) + " ");
        }
        System.out.println();

        long searchKey = 26;
        for (j = 0; j < nElems; j++) {
            if (arr.getElem(j) == searchKey) {
                break;
            }
        }
        if (j == nElems) {
            System.out.println("Can't find " + searchKey);
        } else {
            System.out.println("Found " + searchKey);
        }

        searchKey = 55;
        for (j = 0; j < nElems; j++) {
            if (arr.getElem(j) == searchKey) {
                break;
            }
        }
        for (int k = j; k < nElems - 1; k++) {
            arr.setElem(k, arr.getElem(k + 1));
        }
        nElems--;

        for (j = 0; j < nElems; j++) {
            System.out.print(arr.getElem(j) + " ");
        }
        System.out.println();
    }
}

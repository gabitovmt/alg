package alg.ch02.ex05;

// Объекты как элементы данных
public class ClassDataApp {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        int maxSize = 100;
        var arr = new ClassDataArray(maxSize);

        arr.insert("Evans", "Patty", 24);
        arr.insert("Smith", "Lorraine", 37);
        arr.insert("Yee", "Tom", 43);
        arr.insert("Adams", "Henry", 63);
        arr.insert("Hashimoto", "Sato", 21);
        arr.insert("Stimson", "Michail", 29);
        arr.insert("Velasquez", "Jose", 72);
        arr.insert("Lamarque", "Kenn", 54);
        arr.insert("Vang", "Minh", 22);
        arr.insert("Creswell", "Lucinda", 18);

        arr.displayA();

        String searchKey = "Stimson";
        Person found = arr.find(searchKey);
        if (found != null) {
            System.out.print("Found ");
            found.displayPerson();
        } else {
            System.out.println("Can't find " + searchKey);
        }

        System.out.println("Deleting Smith, Yee, and Creswell");
        arr.delete("Smith");
        arr.delete("Yee");
        arr.delete("Creswell");

        arr.displayA();
    }
}

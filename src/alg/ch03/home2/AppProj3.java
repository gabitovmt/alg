package alg.ch03.home2;

// Программный проект 3.3
public class AppProj3 {

    public static void main(String[] args) {
        var array = new ArrayIns(10);
        array.insert(1);
        array.insert(1);
        array.insert(2);
        array.insert(3);
        array.insert(4);
        array.insert(4);
        array.insert(4);
        array.insert(5);
        array.insert(6);
        array.insert(6);
        array.display();

        array.noDups();
        array.display();
    }
}

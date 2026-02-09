package alg.ch02.ex05;

public class Person {
    private final String lastName;
    private final String firstName;
    private final int age;

    public Person(String last, String first, int age) {
        this.lastName = last;
        this.firstName = first;
        this.age = age;
    }

    public String getLast() {
        return lastName;
    }

    @SuppressWarnings("java:S106")
    public void displayPerson() {
        System.out.println("Last name: " + lastName + ", First name: " + firstName + ", Age: " + age);
    }
}

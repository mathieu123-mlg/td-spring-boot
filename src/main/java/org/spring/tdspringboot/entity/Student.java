package org.spring.tdspringboot.entity;

public class Student {
    private final String reference;
    private final String firstName;
    private final String lastName;
    private final int age;

    public Student(String reference, String firstName, String lastName, int age) {
        this.reference = reference;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getReference() { return reference; }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public int getAge() { return age; }

    @Override
    public String toString() {
        return "{ "+ firstName + " " + lastName + " }";
    }
}

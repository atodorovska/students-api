package com.finki.wp.studentsapi.model;

public class StudentDisplay {

    private String index;
    private String name;
    private String lastName;

    public StudentDisplay() {
    }

    public StudentDisplay(String index, String name, String lastName) {
        this.index = index;
        this.name = name;
        this.lastName = lastName;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "StudentDisplay{" +
                "index='" + index + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

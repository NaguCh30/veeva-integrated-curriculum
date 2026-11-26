package model;

public class Student {
    private int id;
    private String name;
    private double marks;
    private char grade;

    public Student(int id, String name, double marks, char grade) {
        this.id = id;
        this.name = name;
        this.marks = marks;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public char getGrade() {
        return grade;
    }

    public void setMarks(double newMarks) {
        marks = newMarks;
    }

    public void setGrade(char newGrade) {
        grade = newGrade;
    }
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Marks: " + marks + ", Grade: " + grade;
    }
}

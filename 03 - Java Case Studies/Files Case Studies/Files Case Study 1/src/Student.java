public class Student {
    
    private int rollNumber;
    private String name;
    private int age;

    private int telugu;
    private int english;
    private int maths;
    private int science;
    private int social;

    private String grade;

    public Student(int rollNumber, String name, int age, int telugu, int english, int maths, int science, int social,
            String grade) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.telugu = telugu;
        this.english = english;
        this.maths = maths;
        this.science = science;
        this.social = social;
        this.grade = grade;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTelugu() {
        return telugu;
    }

    public int getEnglish() {
        return english;
    }

    public int getMaths() {
        return maths;
    }

    public int getScience() {
        return science;
    }

    public int getSocial() {
        return social;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Roll Number: " + rollNumber +
                "\nName: " + name +
                "\nAge: " + age +
                "\nTelugu: " + telugu +
                "\nEnglish: " + english +
                "\nMaths: " + maths +
                "\nScience: " + science +
                "\nSocial: " + social +
                "\nGrade: " + grade;
    }
}
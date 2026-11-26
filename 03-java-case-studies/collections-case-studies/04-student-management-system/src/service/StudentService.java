package service;

import exception.InvalidMarksException;
import exception.InvalidStudentNameException;
import exception.StudentNotFoundException;
import model.Student;
import repository.StudentRepository;

import java.util.List;
import java.util.Locale;

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getStudentById(int id) throws StudentNotFoundException {
        Student student = studentRepository.getStudentById(id);

        if (student == null) {
            throw new StudentNotFoundException("Student with ID " + id + " not exists!");
        }
        return student;
    }

    public List<Student> getStudentsByName(String name) throws InvalidStudentNameException {
        if (name == null || name.isBlank()) {
            throw new InvalidStudentNameException("Provide valid student name.");
        }
        return studentRepository.getstudentsByName(name.toLowerCase());
    }

    public Student addStudent(int id, String name, double marks) throws InvalidStudentNameException, InvalidMarksException {
        Student existing = studentRepository.getStudentById(id);

        if (existing != null) {
            System.out.println("Student already existed.");
            if (marks < 0 || marks > 100) {
                throw new InvalidMarksException("Marks should be in valid range to update (0 - 100).");
            }
            char grade = calculateGrade(marks);
            studentRepository.updateMarks(id, marks, grade);
            System.out.println("Marks updated.");
            return studentRepository.getStudentById(id);
        }

        if (name == null || name.isBlank()) {
            throw new InvalidStudentNameException("Provide valid student name");
        }
        if (marks < 0 || marks > 100) {
            throw new InvalidMarksException("Enter valid marks for student.");
        }
        char grade = calculateGrade(marks);
        Student student = new Student(id, name, marks, grade);
        return studentRepository.addStudent(student);
    }

    public void removeStudent(int id) throws StudentNotFoundException {
        Student existing = studentRepository.getStudentById(id);
        if (existing == null) {
            throw new StudentNotFoundException("Student with ID " + id + " not exist!");
        }
        studentRepository.removeStudent(id);
    }

    private char calculateGrade(double marks) {
        if (marks >= 90)
            return 'A';
        else if (marks >= 80)
            return 'B';
        else if (marks >= 70)
            return 'C';
        else if (marks >= 60)
            return 'D';
        else if (marks >= 50)
            return 'E';
        else if (marks >= 35)
            return 'P';
        else
            return 'F';
    }
}

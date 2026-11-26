package repository;

import model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentRepository {
    private final Map<Integer, Student> students = new HashMap<>();

    public List<Student> getstudentsByName (String name) {
        return students.values()
                        .stream()
                        .filter(s -> s.getName().toLowerCase().equals(name))
                        .toList();
    }

    public Student getStudentById(int id) {
        return students.get(id);
    }

    public void updateMarks(int id, double marks, char grade) {
        students.get(id).setMarks(marks);
        students.get(id).setGrade(grade);
    }

    public Student addStudent(Student student) {
        students.put(student.getId(), student);
        return students.get(student.getId());
    }

    public void removeStudent(int id) {
        students.remove(id);
    }


}

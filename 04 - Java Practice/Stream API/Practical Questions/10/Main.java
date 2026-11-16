import java.util.List;
import java.util.ArrayList;
import java.util.stream.DoubleStream;
import java.util.OptionalInt;
import java.util.OptionalDouble;

class Student {
    private int id;
    private String name;
    private List<Integer> marks;

    public Student(int id, String name, List<Integer> marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public List<Integer> getMarks() { return marks; }
}

class Main {
    public static void main(String[] args) {
        List<Student> students = List.of(
            new Student(1, "Ravi", List.of(80, 75, 90)),
            new Student(2, "Priya", List.of(95, 85, 92)),
            new Student(3, "Kiran", List.of(60, 70, 65)),
            new Student(4, "Sneha", List.of(88, 91, 84))
        );

        System.out.println("Extract the list of student names using map().");
        students
            .stream()
            .map(Student::getName)
            .forEach(System.out::println);

        System.out.print("\nExtract each student's marks list using map(). Observe the resulting nested structure.");
        students
            .stream()
            .map(s -> s.getMarks())
            .forEach(System.out::println);

        System.out.println("\nExtract all individual marks from all students into a single stream using flatMap()");
        //students.stream().flatMap(s -> s.getMarks().stream()).forEach(System.out::println);
        students
            .stream()
            .map(Student::getMarks)
            .flatMap(List::stream)
            .forEach(System.out::println);

        System.out.println("\nCalculate the total marks obtained by all students using flatMapToInt()");
        int totalMarks = students
            .stream()
            .flatMapToInt(s -> s.getMarks().stream().mapToInt(Integer::intValue))
            .sum();

        //int tot = students.stream().map(Student::getMarks).flatMap(List::stream).mapToInt(Integer::intValue).sum();
        System.out.println("Total Marks = " + totalMarks);

        System.out.println("\nFind the highest mark across all students.");
        OptionalInt highestMarks = students
            .stream()
            .flatMap(s -> s.getMarks().stream())
            .mapToInt(Integer::intValue)
            .max();
        if (highestMarks.isPresent()) {
            System.out.println("Highest Marks: " + highestMarks.getAsInt());
        }

        System.out.println("\nCalculate the average mark across all students.");
        OptionalDouble averageMarks = students
            .stream()
            .map(Student::getMarks)
            .flatMap(List::stream)
            .mapToInt(Integer::intValue)
            .average();
        if (averageMarks.isPresent()) {
            System.out.println("Average Marks: " + averageMarks.getAsDouble());
        }

        System.out.println("Find students whose average marks are greater than 85");
        students.stream()
            .filter(s -> s.getMarks()
                    .stream()
                    .mapToInt(Integer::intValue)
                    .average().orElse(0) > 85)
            .forEach(s -> System.out.println(s.getName()));
    }
}
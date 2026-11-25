import java.util.Comparator;
import java.util.PriorityQueue;

class Patient {
    private int patientId;
    private String name;
    private int severity;
    private int arrivalTime;

    public Patient (int patientId, String name, int severity, int arrivalTime) {
        this.patientId = patientId;
        this.name = name;
        this.severity = severity;
        this.arrivalTime = arrivalTime;
    }

    public int getSeverity() { return severity; }
    public int getArrivalTime() { return arrivalTime; }

    @Override 
    public String toString() {
        return "ID: " + patientId + " | Name: " + name + " | Severity: " + severity + " | Arriavl: " + arrivalTime;
    }
}

class Main {
    public static void main(String[] args) {
        PriorityQueue<Patient> patients = new PriorityQueue<>(
            Comparator.comparingInt(Patient::getSeverity).reversed()
                        .thenComparing(Patient::getArrivalTime)
        );

        patients.add(new Patient(1, "Vikram", 4, 21));
        patients.add(new Patient(2, "Rajesh", 7, 22));
        patients.add(new Patient(3, "Kumar", 1, 1));
        patients.add(new Patient(4, "Jagadheesh", 9, 23));
        patients.add(new Patient(5, "Manoj", 4, 10));
        patients.add(new Patient(6, "David", 5, 6));
        patients.add(new Patient(5, "Ravi", 4, 9));

        while (!patients.isEmpty()) {
            System.out.println(patients.poll());
        }
    }
}
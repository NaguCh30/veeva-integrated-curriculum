import java.util.*;
import java.util.stream.*;

class Patient {
    private int patientId;
    private String patientName;
    private String department;
    private String doctorName;
    private String diagnosis;
    private String treatmentGiven;
    private String patientType;
    private int heartRate;
    private String bp;
    private double sugarValue;
    private String severity;
    private boolean active;

    public Patient(int patientId, String patientName, String department,
                   String doctorName, String diagnosis, String treatmentGiven,
                   String patientType, int heartRate, String bp,
                   double sugarValue, String severity, boolean active) {

        this.patientId = patientId;
        this.patientName = patientName;
        this.department = department;
        this.doctorName = doctorName;
        this.diagnosis = diagnosis;
        this.treatmentGiven = treatmentGiven;
        this.patientType = patientType;
        this.heartRate = heartRate;
        this.bp = bp;
        this.sugarValue = sugarValue;
        this.severity = severity;
        this.active = active;
    }

    public int getPatientId() { return patientId; }
    public String getPatientName() { return patientName; }
    public String getDepartment() { return department; }
    public String getDoctorName() { return doctorName; }
    public String getDiagnosis() { return diagnosis; }
    public String getTreatmentGiven() { return treatmentGiven; }
    public String getPatientType() { return patientType; }
    public int getHeartRate() { return heartRate; }
    public String getBp() { return bp; }
    public double getSugarValue() { return sugarValue; }
    public String getSeverity() { return severity; }
    public boolean isActive() { return active; }

    @Override
    public String toString() {
        return "Patient ID: " + patientId +
               ", Name: " + patientName +
               ", Department: " + department +
               ", Type: " + patientType +
               ", Heart Rate: " + heartRate +
               ", Active: " + active;
    }
}

class Main {
    public static void main(String[] args) {

        List<Patient> patients = List.of(
            new Patient(101, "Rahul", "Neurology", "Dr. Kumar",
                "Brain Injury", "Surgery", "ICU", 120,
                "140/90", 180, "Critical", true),

            new Patient(102, "Priya", "Cardiology", "Dr. Sharma",
                "Heart Disease", "Medication", "ICU", 95,
                "120/80", 110, "Moderate", true),

            new Patient(103, "Kiran", "Neurology", "Dr. Reddy",
                "Migraine", "Medication", "Out", 85,
                "118/78", 95, "Mild", true),

            new Patient(104, "Anjali", "Cardiology", "Dr. Kumar",
                "Heart Attack", "Surgery", "ICU", 130,
                "150/95", 200, "Critical", false),

            new Patient(105, "Ravi", "Orthopedics", "Dr. Rao",
                "Fracture", "Surgery", "In", 90,
                "120/80", 100, "Moderate", true),

            new Patient(106, "Sneha", "Neurology", "Dr. Reddy",
                "Stroke", "Treatment", "ICU", 110,
                "145/90", 160, "Critical", true),

            new Patient(107, "Arjun", "Cardiology", "Dr. Sharma",
                "Hypertension", "Medication", "Out", 105,
                "135/85", 140, "Moderate", true),

            new Patient(108, "Meena", "Neurology", "Dr. Kumar",
                "Seizures", "Medication", "ICU", 88,
                "120/80", 105, "Moderate", false)
        );


        System.out.println("1. Find all active ICU patients");
        List<Patient> activeICUPatients = patients.stream()
            .filter(patient -> patient.isActive())
            .filter(patient -> patient.getPatientType().equals("ICU"))
            .toList();

        activeICUPatients.forEach(System.out::println);

        System.out.println("\n2. Find all active ICU patients with elevated heart rate(heart rate > 100)");
        List<Patient> result = patients.stream()
            .filter(patient ->
                    patient.isActive() &&
                    patient.getPatientType().equals("ICU") &&
                    patient.getHeartRate() > 100
            )
            .toList();

        result.forEach(System.out::println);
    }
}
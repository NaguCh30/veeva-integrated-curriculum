package part_a.q1.model;

public class ContractEmployee extends Employee {
    private double hourlyRate;
    private double hoursWorked;

    public ContractEmployee(int id, String name, String department, double hourlyRate, double hoursWorked) {
        super(id, name, department);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }
}

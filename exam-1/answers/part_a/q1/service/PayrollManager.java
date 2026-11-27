package part_a.q1.service;

import part_a.q1.model.ContractEmployee;
import part_a.q1.model.Employee;
import part_a.q1.model.FullTimeEmployee;
import part_a.q1.exception.InvalidBaseSalaryException;
import part_a.q1.exception.InvalidBonusException;
import part_a.q1.exception.InvalidHourRateException;
import part_a.q1.exception.InvalidHoursWorkedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PayrollManager {

    private final List<Employee> employees= new ArrayList<>();

    public Map<String, Double> getDepartmentWiseTotalSalary() {
        return employees.stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.summingDouble(emp -> emp.calculateSalary()
                                )
                        )
                );
    }

    public List<String> getTopEarners(double threshold) {
        return employees.stream()
                .filter(emp -> emp.calculateSalary() > threshold)
                .map(Employee::getName)
                .toList();
    }

    public void addFullTimeEmployee(int id, String name, String department, double baseSalary, double bonus) throws InvalidBaseSalaryException, InvalidBonusException {
        if (baseSalary <= 0) {
            throw new InvalidBaseSalaryException("Base salary cannot be less than or equal to zero");
        }
        if (bonus < 0) {
            throw new InvalidBonusException("Bonus cannot be less than zero.");
        }
        Employee employee = new FullTimeEmployee(id, name, department, baseSalary, bonus);
        employees.add(employee);
    }

    public void addContractEmployee(int id, String name, String department, double hourlyRate, double hoursWorked) throws InvalidHoursWorkedException, InvalidHourRateException {
        if (hourlyRate <= 0) {
            throw new InvalidHourRateException("Hourly Rate should be greater than 0.");
        }
        if (hoursWorked < 0) {
            throw new InvalidHoursWorkedException("Hours worked cannot be less than zero.");
        }
        Employee employee = new ContractEmployee(id, name, department, hourlyRate, hoursWorked);
        employees.add(employee);
    }
}

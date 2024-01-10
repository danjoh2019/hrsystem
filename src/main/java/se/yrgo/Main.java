package se.yrgo;

import se.yrgo.failure.EmployeeNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Generating a list of employees
        List<Employee> employees = generateEmployees();

        // Printing all employees
        System.out.println("All employees:");
        for (Employee employee : employees) {
            System.out.println(employee);
        }

        // Creating a new Employee as a Programmer with a starting bonus
        System.out.println("\nHiring our newest employee 'Emma Stone', and giving her a starting bonus of 1400SEK");
        Employee emmaStone = new Employee("Emma Stone", 1400);
        emmaStone.addRole(Role.PROGRAMMER);

        employees.add(emmaStone);
        System.out.println(emmaStone);

        try {
            // Promoting an employee
            Employee michaelDouglas = findEmployeeByName(employees, "Michael Douglas");
            int michaelsSalary = michaelDouglas.getSalary();
            System.out.printf("%nPromoting %s to Manager:%n", michaelDouglas.getName());
            michaelDouglas.addRole(Role.MANAGER);

            // Displaying the salary before and after promotion
            System.out.printf("Salary before promotion: %d%n", michaelsSalary);
            System.out.printf("Salary after promotion: %d%n", michaelDouglas.getSalary());

            // Demoting an employee
            System.out.printf("%nDemoting %s from Manager:%n", michaelDouglas.getName());
            michaelDouglas.removeRole(Role.MANAGER);

            // Demoting an employee from a role they don't have
            System.out.printf("%nDemoting %s from Clerk:%n", michaelDouglas.getName());
            michaelDouglas.removeRole(Role.CLERK);

            // Promoting another employee
            Employee jodieFoster = findEmployeeByName(employees, "Jodie Foster");
            int jodiesSalary = jodieFoster.getSalary();
            System.out.printf("%nPromoting %s to Engineer and Manager:%n", jodieFoster.getName());
            jodieFoster.addRole(Role.ENGINEER);
            jodieFoster.addRole(Role.MANAGER);

            // Displaying the salary before and after promotion
            System.out.printf("Salary before promotion: %d%n", jodiesSalary);
            System.out.printf("Salary after promotion: %d%n", jodieFoster.getSalary());

        } catch (EmployeeNotFoundException e) {
            System.err.println(e.getMessage());
        }

        // Printing all employees
        System.out.println("\nAll employees:");
        for (Employee employee : employees) {
            System.out.println(employee);
        }

        try {
            // Trying to get an employee that doesn't exist
            System.out.println("\nTrying to get an employee that doesn't exist with the name of 'Nobody'");
            Employee nonExistentEmployee = findEmployeeByName(employees, "Nobody");
        } catch (EmployeeNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    private static List<Employee> generateEmployees() {
        List<Employee> employees = new ArrayList<>();

        NameGenerator nameGenerator = new NameGenerator();
        List<String> names = nameGenerator.getNames();

        for (String name : names) {
            employees.add(new Employee(name));
        }

        return employees;
    }

    private static Employee findEmployeeByName(List<Employee> employees, String name) throws EmployeeNotFoundException {
        for (Employee employee : employees) {
            if (employee.getName().equals(name)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException("Couldn't find an employee with the name: " + name);
    }
}
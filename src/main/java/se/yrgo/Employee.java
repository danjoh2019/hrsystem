package se.yrgo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Employee {
    private String name;
    private int salary;
    private int bonusSalary;
    private final List<Role> roles;

    public Employee(String name) {
        this.name = setName(name);
        this.roles = new ArrayList<>();
        roles.add(Role.EMPLOYEE);
        updateSalary();
    }
    public Employee(String name, int bonusSalary) {
        this(name);
        addBonusSalary(bonusSalary);
        updateSalary();
    }

    public String getName() {
        return name;
    }

    public void addBonusSalary(int bonusSalary) {
        if(bonusSalary < 0 || bonusSalary > 2000) {
            throw new IllegalArgumentException("You can't add a negative or a bonus higher than 2000SEK");
        }
        this.bonusSalary = bonusSalary;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public int getSalary() {
        return salary;
    }

    public String setName(String name) {
        if(name == null) {
            throw new NullPointerException("Name can't be null");
        }
        this.name = name;
        return name;
    }

    public int setSalary(int salary) {
        if(salary < 0) {
            throw new IllegalArgumentException("Salary can't be negative");
        }
        this.salary = salary;
        updateSalary();
        return salary;
    }

    public void addRole(Role role) {
        // If an employee is promoted to Programmer they will also get the role of Engineer.
        if (role == Role.PROGRAMMER) {
            if (!roles.contains(Role.ENGINEER)) {
                roles.add(Role.ENGINEER);
                System.out.printf("%s has been promoted to Engineer%n", name);
            }
        }

        roles.add(role);
        updateSalary();
        System.out.printf("%s has been promoted to %s%n", name, role.getRoleName());
    }

    public void removeRole(Role role) {
        if(roles.contains(role)) {
            roles.remove(role);
            updateSalary();
            System.out.printf("%s has been demoted from %s%n", name, role.getRoleName());
        } else {
            System.out.printf("%s doesn't have the %s role%n", name, role.getRoleName());
        }
    }

    private void updateSalary() {
        salary = bonusSalary + roles.stream().mapToInt(Role::getSalary).sum();
    }

    @Override
    public String toString() {
        String employeeRoles = roles.stream().map(Role::toString).collect(Collectors.joining(", "));

        return "Name: " + name +
                ", Salary: " + salary +
                ", Roles: " + employeeRoles;
    }
}

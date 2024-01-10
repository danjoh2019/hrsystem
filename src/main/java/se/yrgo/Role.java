package se.yrgo;

public enum Role {
    EMPLOYEE("Employee", 25000),
    MANAGER("Manager", 4500),
    ENGINEER("Engineer", 2000),
    CLERK("Clerk", 200),
    PROGRAMMER("Programmer", 1000);

    private final String roleName;
    private final int salary;
    Role(String roleName, int salary) {
        this.roleName = roleName;
        this.salary = salary;
    }

    public String getRoleName() {
        return roleName;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return roleName;
    }
}

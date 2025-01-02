public class EmployeePayrollData {
    int id;
    String name;
    double salary;

    // Constructor
    public EmployeePayrollData(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // Convert Employee object to String for writing to file
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Salary: " + salary;
    }
}
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Create some test EmployeePayrollData objects
        EmployeePayrollData emp1 = new EmployeePayrollData(1, "John Doe", 50000);
        EmployeePayrollData emp2 = new EmployeePayrollData(2, "Jane Smith", 60000);
        EmployeePayrollData emp3 = new EmployeePayrollData(3, "Bob Brown", 45000);

        // Store these objects in a list
        List<EmployeePayrollData> employeeList = new ArrayList<>();
        employeeList.add(emp1);
        employeeList.add(emp2);
        employeeList.add(emp3);

        // Write Employee Payroll to a file
        writeEmployeePayrollToFile(employeeList, "employeePayroll.txt");

        // Count number of entries in the file to ensure the operation worked
        countFileEntries("employeePayroll.txt");
    }

    // Method to write employee payroll data to a file
    private static void writeEmployeePayrollToFile(List<EmployeePayrollData> employeeList, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (EmployeePayrollData employee : employeeList) {
                writer.write(employee.toString());
                writer.newLine();
            }
            System.out.println("Employee payroll data written to file successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Method to count the number of entries in the file
    private static void countFileEntries(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int lineCount = 0;
            while (reader.readLine() != null) {
                lineCount++;
            }
            System.out.println("Number of entries in the file: " + lineCount);
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }
}
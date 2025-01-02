import java.io.*;
import java.util.*;

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
        String filename = "employeePayroll.txt";
        writeEmployeePayrollToFile(employeeList, filename);

        // Show the number of entries in the file
        showNumberOfEntriesInFile(filename);
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

    // Method to count and show the number of entries in the file
    private static void showNumberOfEntriesInFile(String filename) {
        int entryCount = countFileEntries(filename);
        System.out.println("\nNumber of entries in the file: " + entryCount);
    }

    // Method to count the number of entries in the file
    private static int countFileEntries(String filename) {
        int lineCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while (reader.readLine() != null) {
                lineCount++;
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return lineCount;
    }
}
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Use the correct path to the file
        String filename = "src/employeePayroll.txt"; // Adjust based on your folder structure

        // Check if the file exists
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("File does not exist: " + filename);
            return; // Exit if file does not exist
        }

        // Read Employee Payroll Data from the file and load it into the list
        List<EmployeePayrollData> employeeList = readEmployeePayrollFromFile(filename);

        // Perform analysis on the data
        if (!employeeList.isEmpty()) {
            System.out.println("\nEmployee Payroll Data:");
            employeeList.forEach(System.out::println);

            // Perform analysis (e.g., count employees)
            System.out.println("\nTotal number of employees: " + employeeList.size());
        } else {
            System.out.println("No data found in the file.");
        }
    }

    // Method to read employee payroll data from a file
    private static List<EmployeePayrollData> readEmployeePayrollFromFile(String filename) {
        List<EmployeePayrollData> employeeList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the string to get id, name, and salary
                String[] employeeData = line.split(", ");
                int id = Integer.parseInt(employeeData[0].split(": ")[1]);
                String name = employeeData[1].split(": ")[1];
                double salary = Double.parseDouble(employeeData[2].split(": ")[1]);

                // Add the employee data to the list
                employeeList.add(new EmployeePayrollData(id, name, salary));
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return employeeList;
    }
}
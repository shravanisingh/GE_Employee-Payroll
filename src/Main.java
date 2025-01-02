import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        EmployeePayrollService employeePayrollService = new EmployeePayrollService(employeePayrollList);

        Scanner consoleInputReader = new Scanner(System.in);

        // Read data from console
        employeePayrollService.readEmployeePayrollData(consoleInputReader);

        // Write data to console
        employeePayrollService.writeEmployeePayrollData();

        consoleInputReader.close();
    }
}
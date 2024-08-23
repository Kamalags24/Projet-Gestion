import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeManager implements EmployeeOperations {
  private List<Employee> employees;
  private Scanner scanner;
  private static final String FILE_NAME = "employees.txt";

  public EmployeeManager() {
    employees = new ArrayList<>();
    scanner = new Scanner(System.in);
    // loadEmployeesFromFile();
  }

  @Override
  public void addEmployee(Employee employee) {
    employees.add(employee);
    System.out.println("Employee added successfully.");
    saveEmployeesToFile();
  }

  @Override
  public void removeEmployee(int id) {
    Employee employee = findEmployeeById(id);
    if (employee != null) {
      employees.remove(employee);
      System.out.println("Employee removed successfully.");
      saveEmployeesToFile();
    } else {
      System.out.println("Employee with ID " + id + " not found.");
    }
  }

  @Override
  public void displayAllEmployees() {
    if (employees.isEmpty()) {
      System.out.println("No employees to display.");
    } else {
      for (Employee employee : employees) {
        employee.displayDetails();
        System.out.println();
      }
    }
  }

  @Override
  public void displayEmployeeById(int id) {
    Employee employee = findEmployeeById(id);
    if (employee != null) {
      employee.displayDetails();
    } else {
      System.out.println("Employee with ID " + id + " not found.");
    }
  }

  @Override
  public void updateEmployee(int id) {
    // public void updateEmployee(int id) {
      Employee employee = findEmployeeById(id);
      if (employee != null) {
        System.out.println("Enter new details for Employee ID: " + id);
        System.out.print("Enter new Employee Name (current: " + employee.getName() + "): ");
        String name = scanner.nextLine();
        System.out.print("Enter new Employee Position (current: " + employee.getPosition() + "): ");
        String position = scanner.nextLine();
        System.out.print("Enter new Employee Salary (current: " + employee.getSalary() + "): ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // Consomme la nouvelle ligne
  
        employee.setName(name);
        employee.setPosition(position);
        employee.setSalary(salary);
  
        if (employee instanceof Manager) {
          System.out.print("Enter new Number of Employees under Manager (current: "
              + ((Manager) employee).getNumberOfEmployees() + "): ");
          int numberOfEmployees = scanner.nextInt();
          scanner.nextLine(); // Consomme la nouvelle ligne
          ((Manager) employee).setNumberOfEmployees(numberOfEmployees);
        } else if (employee instanceof Developer) {
          System.out.print(
              "Enter new Specialized Language (current: " + ((Developer) employee).getSpecializedLanguage() + "): ");
          String specializedLanguage = scanner.nextLine();
          ((Developer) employee).setSpecializedLanguage(specializedLanguage);
        }
  
        System.out.println("Employee updated successfully.");
        saveEmployeesToFile();
      } else {
        System.out.println("Employee with ID " + id + " not found.");
      }
    // }
    // throw new UnsupportedOperationException("Unimplemented method 'updateEmployeeOption'");
  }

  

  private Employee findEmployeeById(int id) {
    for (Employee employee : employees) {
      if (employee.getId() == id) {
        return employee;
      }
    }
    return null;
  }

  private void showMenu() {
    System.out.println("\nEmployee Management System:");
    System.out.println("1. Add Employee");
    System.out.println("2. Remove Employee");
    System.out.println("3. Display All Employees");
    System.out.println("4. Display Employee by ID");
    System.out.println("5. Update Employee");
    System.out.println("6. Exit");
    System.out.print("Choose an option: ");
  }

  private void handleUserInput() {
    boolean exit = false;
    while (!exit) {
      showMenu();
      int choice = scanner.nextInt();
      scanner.nextLine(); // Consomme la nouvelle ligne

      switch (choice) {
        case 1:
          addEmployeeOption();
          break;
        case 2:
          removeEmployeeOption();
          break;
        case 3:
          displayAllEmployees();
          break;
        case 4:
          displayEmployeeByIdOption();
          break;
        case 5:
          updateEmployeeOption();
          break;
        case 6:
          exit = true;
          System.out.println("Exiting...");
          break;
        default:
          System.out.println("Invalid option. Please try again.");
      }
    }
  }

  private void addEmployeeOption() {
    System.out.print("Enter Employee ID: ");
    int id = scanner.nextInt();
    scanner.nextLine(); // Consomme la nouvelle ligne
    System.out.print("Enter Employee Name: ");
    String name = scanner.nextLine();
    System.out.print("Enter Employee Position (Manager/Developer): ");
    String position = scanner.nextLine();
    System.out.print("Enter Employee Salary: ");
    double salary = scanner.nextDouble();
    scanner.nextLine(); // Consomme la nouvelle ligne

    Employee employee;
    if (position.equalsIgnoreCase("Manager")) {
      System.out.print("Enter Number of Employees under Manager: ");
      int numberOfEmployees = scanner.nextInt();
      scanner.nextLine(); // Consomme la nouvelle ligne
      employee = new Manager(id, name, position, salary, numberOfEmployees);
    } else if (position.equalsIgnoreCase("Developer")) {
      System.out.print("Enter Specialized Language: ");
      String specializedLanguage = scanner.nextLine();
      employee = new Developer(id, name, position, salary, specializedLanguage);
    } else {
      System.out.println("Invalid position entered. Employee not added.");
      return;
    }

    addEmployee(employee);
  }

  private void removeEmployeeOption() {
    System.out.print("Enter Employee ID to remove: ");
    int id = scanner.nextInt();
    scanner.nextLine(); // Consomme la nouvelle ligne
    removeEmployee(id);
  }

  private void displayEmployeeByIdOption() {
    System.out.print("Enter Employee ID to display: ");
    int id = scanner.nextInt();
    scanner.nextLine(); // Consomme la nouvelle ligne
    displayEmployeeById(id);
  }

  private void updateEmployeeOption() {
    System.out.print("Enter Employee ID to update: ");
    int id = scanner.nextInt();
    scanner.nextLine(); // Consomme la nouvelle ligne
    updateEmployee(id);
  }

  private void saveEmployeesToFile() {
    try (FileWriter writer = new FileWriter(FILE_NAME)) {
      for (Employee employee : employees) {
        writer.write(employeeToString(employee) + "\n");
      }
    } catch (IOException e) {
      System.out.println("Error saving employees to file: " + e.getMessage());
    }
  }

  private void loadEmployeesFromFile() {
    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
      String line;
      while ((line = reader.readLine()) != null) {
        Employee employee = stringToEmployee(line);
        if (employee != null) {
          employees.add(employee);
        }
      }
    } catch (IOException e) {
      System.out.println("Error loading employees from file: " + e.getMessage());
    }
  }

  private String employeeToString(Employee employee) {
    if (employee instanceof Manager) {
      Manager manager = (Manager) employee;
      return "Manager," + manager.getId() + "," + manager.getName() + "," + manager.getPosition() + ","
          + manager.getSalary() + "," + manager.getNumberOfEmployees();
    } else if (employee instanceof Developer) {
      Developer developer = (Developer) employee;
      return "Developer," + developer.getId() + "," + developer.getName() + "," + developer.getPosition() + ","
          + developer.getSalary() + "," + developer.getSpecializedLanguage();
    }
    return "";
  }

  private Employee stringToEmployee(String str) {
    String[] parts = str.split("\n");
    if (parts.length == 0) {
      return null;
    }

    String type = parts[0];
    int id = Integer.parseInt(parts[1]);
    String name = parts[2];
    String position = parts[3];
    double salary = Double.parseDouble(parts[4]);

    if (type.equals("Manager")) {
      int numberOfEmployees = Integer.parseInt(parts[5]);
      return new Manager(id, name, position, salary, numberOfEmployees);
    } else if (type.equals("Developer")) {
      String specializedLanguage = parts[5];
      return new Developer(id, name, position, salary, specializedLanguage);
    }

    return null;
  }

  public static void main(String[] args) {
    EmployeeManager manager = new EmployeeManager();
    manager.handleUserInput();
  }

  
}
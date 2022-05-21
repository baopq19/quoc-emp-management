package com.quocvuong;

import com.quocvuong.dao.EmployeeDAO;
import com.quocvuong.model.Employee;
import com.quocvuong.utils.Constant;
import com.quocvuong.utils.FileHandler;
import com.quocvuong.utils.InputHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final EmployeeDAO employeeDAO = new EmployeeDAO();
    private static List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        mainProcess();

//        Employee emp1 = new Employee();
//        emp1.setName("One");
//        emp1.setDateOfBirth("19/10/96");
//        emp1.setHourlyRate("20.5");
//
//        Employee emp2 = new Employee();
//        emp2.setName("Two");
//        emp2.setDateOfBirth("19/10/92");
//        emp2.setHourlyRate("25");
//
//        Employee emp3 = new Employee();
//        emp3.setName("Three");
//        emp3.setDateOfBirth("22/10/92");
//        emp3.setHourlyRate("23");
//
//        EmployeeDAO employeeDAO = new EmployeeDAO();
//        employeeDAO.addEmployee(employees, emp1);
//        employeeDAO.addEmployee(employees, emp2);
//        employeeDAO.addEmployee(employees, emp3);
//
//        System.out.println(employees);
    }

    static void mainProcess() {
        loadData();
        int userSelection;

        // Keep running till user choose to exit
        do {
            System.out.println("---------------------------------");
            printMenu();
            // Keep asking till user input a valid selection
            do {
                System.out.print("Please input your selection (number): ");
                userSelection = InputHandler.getPositiveIntegerInput();
            } while (userSelection < 1 || userSelection > 5);

            switch (userSelection) {
                case (1):
                    System.out.println(Constant.OPT_1);
                    handleAddEmployee();
                    break;
                case (2):
                    System.out.println(Constant.OPT_2);
                    handlePaySalary();
                    break;
                case (3):
                    System.out.println(Constant.OPT_3);
                    handleShowListEmployees();
                    break;
                case (4):
                    System.out.println(Constant.OPT_4);
                    System.out.println("Developing...");
                    break;
                case (5):
                    System.out.println(Constant.OPT_5);
                    handleExit();
                    return;
                default:
                    break;
            }
        } while (userSelection != 5);


    }

    static void loadData() {
        System.out.println("Loading data...");
        employees = FileHandler.readEmployees();
        System.out.println("Loading success.");
        System.out.println("You currently have " + employees.size() + " employees in database");
    }

    static void printMenu() {
        System.out.println(Constant.OPT_1);
        System.out.println(Constant.OPT_2);
        System.out.println(Constant.OPT_3);
        System.out.println(Constant.OPT_4);
        System.out.println(Constant.OPT_5);
    }

    static void handleAddEmployee() {
        System.out.println("Input new employee infos");

        System.out.print("Name: ");
        String name = InputHandler.getStringInput();
        System.out.print("Hourly rate: ");
        int hourlyRate = InputHandler.getPositiveIntegerInput();
        System.out.print("Year of birth: ");
        int yearOfBirth = InputHandler.getPositiveIntegerInput();
        Employee newEmp = new Employee(name, hourlyRate, yearOfBirth);

        employeeDAO.addEmployee(employees, newEmp);
        // Save to file
        FileHandler.writeEmployees(employees);
        System.out.println("New user added: " + employees.get(employees.size()-1));
    }

    static void handlePaySalary() {
        // Check employee list
        if (employees.size() < 1) {
            System.out.println("You dont have any employee in database, please add some first!");
            return;
        }

        // If its good
        int inpId;
        Employee emp = null;

        // Keep asking till user input a valid user id
        do {
            System.out.print("Input user's id: ");
            inpId = InputHandler.getPositiveIntegerInput();
            for (Employee e : employees) {
                if(e.getId() == inpId) {
                    emp = e;
                    break;
                }
            }
            if(emp == null) System.out.println("Cannot find any employee with id: " + inpId);
        } while(emp == null);

        // Found the user
        System.out.println("Found user: " + emp);
        System.out.print("Hours " + emp.getName() + " has worked (hourly rate $" + emp.getHourlyRate() +"): ");
        int workHours = InputHandler.getPositiveIntegerInput();
        int salary = emp.getHourlyRate() * workHours;
        System.out.println("Salary of " + emp.getName() + " this month is: $" + salary);
    }

    static void handleShowListEmployees() {
        employees.forEach(System.out::println);
    }

    static void handleExit() {
        System.out.println("Saving data...");
        FileHandler.writeEmployees(employees);
        System.out.println("Done, bye bye");
    }

}




















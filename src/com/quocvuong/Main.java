package com.quocvuong;

import com.quocvuong.dao.EmployeeDAO;
import com.quocvuong.model.Employee;
import com.quocvuong.utils.FileHandler;
import com.quocvuong.utils.InputHandler;

import java.util.List;
import java.util.Scanner;

public class Main {

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
        List<Employee> employees = loadData();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        int userSelection;

        printMenu();
        do {
            System.out.print("Please input your selection (number): ");
            userSelection = InputHandler.getPositiveIntegerInput();
        } while (userSelection < 1 || userSelection > 4);

        switch (userSelection) {
            case (1):
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
                break;
            case (2):
                System.out.print("Input user's Id: ");

                break;
            case (3):
                break;
            case (4):
                return;
            default:
                break;
        }
    }

    static List<Employee> loadData() {
        System.out.println("Loading data...");
        List<Employee> employees = FileHandler.readEmployees();
        System.out.println("Loading success.");
        System.out.println("You currently have " + employees.size() + " employees in database");
        System.out.println("---------------------------------");
        return employees;
    }

    static void printMenu() {
        String menu = "1. Add new employee \n" +
                "2. Pay salary \n" +
                "3. Show history \n" +
                "4. Exit";
        System.out.println(menu);
    }


}




















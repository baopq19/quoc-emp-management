package com.quocvuong;

import com.quocvuong.dao.EmployeeDAO;
import com.quocvuong.dao.PaymentDAO;
import com.quocvuong.model.Employee;
import com.quocvuong.model.Payment;
import com.quocvuong.utils.Constant;
import com.quocvuong.utils.FileHandler;
import com.quocvuong.utils.InputHandler;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static final EmployeeDAO employeeDAO = new EmployeeDAO();
    private static final PaymentDAO paymentDAO = new PaymentDAO();
    private static List<Employee> employees = new ArrayList<>();
    private static List<Payment> payments = new ArrayList<>();

    public static void main(String[] args) {
        mainProcess();
    }

    static void mainProcess() {
        loadData();
        int userSelection;

        // Keep running till user choose to exit
        do {
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
                    handleShowPaymentHistory();
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
        payments = FileHandler.readPayments();
        System.out.println("Loading success.");
        System.out.println("You currently have " + employees.size() + " employees in database");
    }

    static void printMenu() {
        System.out.println("---------------------------------");
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

        // If employee exist
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

        int year;
        int month;
        int currentYear = Year.now().getValue();
        do {
            System.out.print("Year: ");
            year = InputHandler.getPositiveIntegerInput();
            if(year < 1900 || year > currentYear) System.out.println("Year cannot be later than current year or sooner than 1900 (1900 - " + currentYear + ")");
        } while (year < 1900 || year > currentYear);

        do {
            System.out.print("Month: ");
            month = InputHandler.getPositiveIntegerInput();
            if(month < 1 || month > 12) System.out.println("Month must be (01-12)");
        } while (month < 1 || month > 12);

        String paymentId = Payment.createId(emp.getId(), month, year);

        // Check if payment existed
        boolean isExist = payments.stream().anyMatch(p -> paymentId.equals(p.getPaymentId()));
        if(isExist) {
            System.out.println("You already pay " + emp.getName() + " this month");
            return;
        }

        // If payment doesnt exist
        int workHours;
        do {
            System.out.print("Hours " + emp.getName() + " has worked (hourly rate $" + emp.getHourlyRate() +"): ");
            workHours = InputHandler.getPositiveIntegerInput();
            if(workHours < 1 || workHours > 160) System.out.println("Work hours must me (1 - 160)");
        } while (workHours < 1 || workHours > 160);

        // All goods
        int salary = emp.getHourlyRate() * workHours;
        System.out.println("Salary of " + emp.getName() + " this month is: $" + salary);

        Payment payment = new Payment();
        payment.setPaymentId(paymentId);
        payment.setWorkHours(workHours);
        payment.setHourlyRate(emp.getHourlyRate());
        payment.setSalary(salary);

        payments.add(payment);
        FileHandler.writePayments(payments);
        System.out.println("Payment for " + emp.getName() + " on " + month + "/" + year + " is created");
    }

    static void handleShowListEmployees() {
        employees.forEach(System.out::println);
    }

    static void handleShowPaymentHistory() {
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

        // Found employee
        int finalInpId = inpId;
        List<Payment> empPayments = payments.stream().filter(p -> p.getPaymentId().startsWith("" + finalInpId)).collect(Collectors.toList());

        if(empPayments.size() < 1) {
            System.out.println(emp.getName() + " did not have any salary history");
            return;
        }
        System.out.println(emp.getName() + " salary history: ");
        empPayments.forEach(p -> {
            String[] splittedId = p.getPaymentId().split("_");
            String date = splittedId[1];
            // Add '/' into date
            date = date.substring(0, 2) + "/" + date.substring(2);
            System.out.println("********************");
            System.out.println(date);
            System.out.println("Salary: $" + p.getSalary());
            System.out.println("Work hours: " + p.getWorkHours());
            System.out.println("Hourly rate: " + p.getHourlyRate());
            System.out.println("********************");
        });
    }

    static void handleExit() {
        System.out.println("Saving data...");
        FileHandler.writeEmployees(employees);
        FileHandler.writePayments(payments);
        System.out.println("Done, bye bye");
    }

}




















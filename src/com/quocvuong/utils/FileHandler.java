package com.quocvuong.utils;

import com.quocvuong.model.Employee;
import com.quocvuong.model.Payment;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileHandler {
    public static void writeEmployees(List<Employee> employees) {
        try {
            FileWriter fileWriter = new FileWriter(Constant.EMPLOYEE_FILE_PATH);
            String employeeString = employees.stream().map(Employee::toSaveString).collect(Collectors.joining("\n"));
            fileWriter.write(employeeString);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Employee> readEmployees() {
        List<Employee> employees = new ArrayList<>();
        try {
            File file = new File(Constant.EMPLOYEE_FILE_PATH);
            if(!file.exists()) {
                file.createNewFile();
            }
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while((line = bufferedReader.readLine()) != null) {
                Employee emp = new Employee();
                String[] empProps = line.split(",");

                emp.setId(Integer.parseInt(empProps[0]));
                emp.setName(empProps[1]);
                emp.setHourlyRate(Integer.parseInt(empProps[2]));
                emp.setYearOfBirth(Integer.parseInt(empProps[3]));
                employees.add(emp);
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public static void writePayments(List<Payment> payments) {
        try {
            FileWriter fileWriter = new FileWriter(Constant.PAYMENT_FILE_PATH);
            String paymentString = payments.stream().map(Payment::toSaveString).collect(Collectors.joining("\n"));
            fileWriter.write(paymentString);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Payment> readPayments() {
        List<Payment> payments = new ArrayList<>();
        try {
            File file = new File(Constant.PAYMENT_FILE_PATH);
            if(!file.exists()) {
                file.createNewFile();
            }
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while((line = bufferedReader.readLine()) != null) {
                Payment payment = new Payment();
                String[] paymentProps = line.split(",");

                payment.setPaymentId(paymentProps[0]);
                payment.setHourlyRate(Integer.parseInt(paymentProps[1]));
                payment.setWorkHours(Integer.parseInt(paymentProps[2]));
                payment.setSalary(Integer.parseInt(paymentProps[3]));
                payments.add(payment);
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return payments;
    }
}

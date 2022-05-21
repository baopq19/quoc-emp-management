package com.quocvuong.utils;

import com.quocvuong.model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
            FileReader fileReader = new FileReader(Constant.EMPLOYEE_FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";

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
}

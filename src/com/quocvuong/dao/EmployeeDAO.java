package com.quocvuong.dao;

import com.quocvuong.model.Employee;

import java.util.List;

public class EmployeeDAO {
    public void addEmployee(List<Employee> employees, Employee emp) {
        int newId = 1;
        if(employees.size() > 0) {
            newId = Employee.getLargestId(employees) + 1;
        }
        emp.setId(newId);
        employees.add(emp);
    }
}

package com.quocvuong.model;

import java.util.Collections;
import java.util.List;

public class Employee {
    private Integer id;
    private String name;
    private Integer hourlyRate;
    private Integer yearOfBirth;

    public Employee() {
    }

    public Employee(String name, Integer hourlyRate, Integer yearOfBirth) {
        this.name = name;
        this.hourlyRate = hourlyRate;
        this.yearOfBirth = yearOfBirth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Integer hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public static int getLargestId(List<Employee> employees) {
        Employee result;
        result = Collections.max(employees, (e1, e2) -> {
            if(e1.getId() > e2.getId()) return 1;
            return -1;
        });

        return result.getId();
    }

    @Override
    public String toString() {
        return "Employee (" + id +"): " +
                " name: " + name +
                ", hourly rate: " + hourlyRate +
                ", year of birth: " + yearOfBirth;
    }

    public String toSaveString() {
        return id + "," + name + "," + hourlyRate + "," + yearOfBirth;
    }
}

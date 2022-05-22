package com.quocvuong.model;

public class Payment {
    private String paymentId;
    private Integer hourlyRate;
    private Integer workHours;
    private Integer salary;

    public Payment() {
    }

    public Payment(String paymentId, Integer hourlyRate, Integer workHours, Integer salary) {
        this.paymentId = paymentId;
        this.hourlyRate = hourlyRate;
        this.workHours = workHours;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", hourlyRate=" + hourlyRate +
                ", workHours=" + workHours +
                ", salary=" + salary +
                '}';
    }

    public static String createId(int userId, int month, int year) {
        String date = "" + month + year;
        if(date.length() == 5) date = "0" + date;
        return  userId + "_" + date;
    }

    public String toSaveString() {
        return paymentId + "," + hourlyRate + "," + workHours + "," + salary;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Integer hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public Integer getWorkHours() {
        return workHours;
    }

    public void setWorkHours(Integer workHours) {
        this.workHours = workHours;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}

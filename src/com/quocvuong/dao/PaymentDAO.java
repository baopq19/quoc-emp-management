package com.quocvuong.dao;

import com.quocvuong.model.Payment;

import java.util.List;

public class PaymentDAO {
    public void addPayment(List<Payment> payments, Payment payment) {
        payments.add(payment);
    }
}

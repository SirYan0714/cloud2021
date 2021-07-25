package com.xtan.mycloud.service;

import com.xtan.mycloud.entities.Payment;

public interface PaymentService {
    public int addPayment(Payment payment);
    public Payment getPaymentById(Long id);
}

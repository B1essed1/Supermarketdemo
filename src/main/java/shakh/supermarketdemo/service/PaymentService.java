package shakh.supermarketdemo.service;

import shakh.supermarketdemo.data.Payment;

public interface PaymentService
{
    Payment getPaymentById(Long id);
    Payment save(Payment payment);
}

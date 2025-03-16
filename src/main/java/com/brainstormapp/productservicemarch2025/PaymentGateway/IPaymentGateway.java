package com.brainstormapp.productservicemarch2025.PaymentGateway;

public interface IPaymentGateway {
    public String paymentLink(String name, String email, long amount, String orderId, String phoneNumber);
}

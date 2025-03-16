package com.brainstormapp.productservicemarch2025.service;

public interface PaymentServiceInterface {

    String initiatePayment(String name, String email, long amount, String orderId, String phoneNumber);
}

package com.brainstormapp.productservicemarch2025.service;
import com.brainstormapp.productservicemarch2025.PaymentGateway.IPaymentGateway;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements PaymentServiceInterface {

    private final IPaymentGateway paymentGateway;

    public PaymentService(IPaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    @Override
    public String initiatePayment(String name, String email, long amount, String orderId, String phoneNumber) {
        return paymentGateway.paymentLink(name, email, amount, orderId, phoneNumber);
    }
}

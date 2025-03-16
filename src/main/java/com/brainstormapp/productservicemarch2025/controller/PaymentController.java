package com.brainstormapp.productservicemarch2025.controller;


import com.brainstormapp.productservicemarch2025.DTO.PaymentDTO;
import com.brainstormapp.productservicemarch2025.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payment")
    public String initiatePayment(@RequestBody PaymentDTO paymentDTO) {
        return paymentService.initiatePayment(paymentDTO.getName(), paymentDTO.getEmail(), paymentDTO.getAmount(),
                paymentDTO.getOrderId(), paymentDTO.getPhoneNumber());
    }
}

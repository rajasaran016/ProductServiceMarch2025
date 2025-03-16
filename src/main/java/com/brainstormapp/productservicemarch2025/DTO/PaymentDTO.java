package com.brainstormapp.productservicemarch2025.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDTO {

    private long amount;
    private String orderId;
    private String phoneNumber;
    private String name;
    private String email;
}

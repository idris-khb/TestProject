package com.almaplus.testproject.dto;

import com.almaplus.testproject.entity.enums.Currency;
import com.almaplus.testproject.entity.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentResponse {

    private Long paymentId;
    private BigDecimal amount;
    private Currency currency;
    private String description;
    private String clientId;
    private PaymentStatus status;
}
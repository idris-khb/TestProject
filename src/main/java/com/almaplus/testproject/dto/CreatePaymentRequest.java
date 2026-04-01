package com.almaplus.testproject.dto;

import com.almaplus.testproject.entity.enums.Currency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CreatePaymentRequest {

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotNull
    private Currency currency;

    private String description;

    @NotBlank
    private String clientId;
}
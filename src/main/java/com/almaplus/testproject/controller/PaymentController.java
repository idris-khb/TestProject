package com.almaplus.testproject.controller;

import com.almaplus.testproject.dto.CreatePaymentRequest;
import com.almaplus.testproject.dto.PaymentResponse;
import com.almaplus.testproject.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    @PostMapping
    public PaymentResponse create(@Valid @RequestBody CreatePaymentRequest request) {
        return service.create(request);
    }

    @GetMapping("/{id}")
    public PaymentResponse get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("/{id}/confirm")
    public PaymentResponse confirm(@PathVariable Long id) {
        return service.confirm(id);
    }

    @PostMapping("/{id}/cancel")
    public PaymentResponse cancel(@PathVariable Long id) {
        return service.cancel(id);
    }
}
package com.almaplus.testproject.controller;

import com.almaplus.testproject.dto.PaymentResponse;
import com.almaplus.testproject.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final PaymentService service;

    @GetMapping("/{clientId}/payments")
    public List<PaymentResponse> getClientPayments(@PathVariable String clientId) {
        return service.getByClient(clientId);
    }
}

package com.almaplus.testproject.service;

import com.almaplus.testproject.dto.CreatePaymentRequest;
import com.almaplus.testproject.dto.PaymentResponse;
import com.almaplus.testproject.entity.Client;
import com.almaplus.testproject.entity.Payment;
import com.almaplus.testproject.entity.enums.PaymentStatus;
import com.almaplus.testproject.exception.BadRequestException;
import com.almaplus.testproject.exception.NotFoundException;
import com.almaplus.testproject.repository.ClientRepository;
import com.almaplus.testproject.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ClientRepository clientRepository;

    @Transactional
    public PaymentResponse create(CreatePaymentRequest request) {

        Client client = clientRepository.findByExternalId(request.getClientId())
                .orElseGet(() -> {
                    Client newClient = new Client();
                    newClient.setExternalId(request.getClientId());
                    return clientRepository.save(newClient);
                });

        Payment payment = new Payment();
        payment.setAmount(request.getAmount());
        payment.setCurrency(request.getCurrency());
        payment.setDescription(request.getDescription());
        payment.setClient(client);
        payment.setStatus(PaymentStatus.PENDING);
        payment.setCreatedAt(LocalDateTime.now());

        return map(paymentRepository.save(payment));
    }

    public PaymentResponse getById(Long id) {
        Payment payment = getEntity(id);
        return map(payment);
    }

    @Transactional
    public PaymentResponse confirm(Long id) {
        Payment payment = getEntity(id);

        if (!payment.getStatus().canConfirm()) {
            throw new BadRequestException("Payment cannot be confirmed");
        }

        payment.setStatus(PaymentStatus.CONFIRMED);
        return map(paymentRepository.save(payment));
    }

    @Transactional
    public PaymentResponse cancel(Long id) {
        Payment payment = getEntity(id);

        if (!payment.getStatus().canCancel()) {
            throw new BadRequestException("Payment cannot be canceled");
        }

        payment.setStatus(PaymentStatus.CANCELED);
        return map(paymentRepository.save(payment));
    }

    public List<PaymentResponse> getByClient(String clientId) {

        Client client = clientRepository.findByExternalId(clientId)
                .orElseThrow(() -> new NotFoundException("Client not found"));

        return paymentRepository.findByClient(client)
                .stream()
                .map(this::map)
                .toList();
    }

    private Payment getEntity(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Payment not found"));
    }

    private PaymentResponse map(Payment payment) {
        PaymentResponse response = new PaymentResponse();
        response.setPaymentId(payment.getId());
        response.setAmount(payment.getAmount());
        response.setCurrency(payment.getCurrency());
        response.setDescription(payment.getDescription());
        response.setClientId(payment.getClient().getExternalId());
        response.setStatus(payment.getStatus());
        return response;
    }
}
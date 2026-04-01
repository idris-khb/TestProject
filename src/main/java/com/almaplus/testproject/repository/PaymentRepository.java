package com.almaplus.testproject.repository;


import com.almaplus.testproject.entity.Client;
import com.almaplus.testproject.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByClient(Client client);
}

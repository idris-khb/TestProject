package com.almaplus.testproject.repository;

import com.almaplus.testproject.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByExternalId(String externalId);
}

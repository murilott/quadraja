package com.example.reserva.infrastructure.persistence.reserva;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.reserva.domain.reserva.Reserva;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataReservaJpa extends JpaRepository<Reserva, UUID> {
    // Optional<Quadra> findByName(String name);
    // boolean existsByName(String name);
}

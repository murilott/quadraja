package com.example.reserva.domain.reserva;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ReservaRepository {
    // boolean existsByName(String name);
    // Optional<Reserva> findByName(String name);
    Reserva save(Reserva reserva);
    Optional<Reserva> findById(UUID id);
    Page<Reserva> findAll(Pageable pageable);
}

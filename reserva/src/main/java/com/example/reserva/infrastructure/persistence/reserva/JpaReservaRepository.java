package com.example.reserva.infrastructure.persistence.reserva;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.example.reserva.domain.reserva.Reserva;
import com.example.reserva.domain.reserva.ReservaRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaReservaRepository implements ReservaRepository {
    private final SpringDataReservaJpa jpa;

    public JpaReservaRepository(SpringDataReservaJpa jpa) {
        this.jpa = jpa;
    }
    // @Override
    // public boolean existsByName(String name) {
    //     return jpa.existsByName(name);
    // }

    // @Override
    // public Optional<Reserva> findByName(String name) {
    //     return jpa.findByName(name);
    // }

    @Override
    public Reserva save(Reserva user) {
        return jpa.save(user);
    }

    @Override
    public Optional<Reserva> findById(UUID id) {
        return jpa.findById(id);
    }

    @Override
    public Page<Reserva> findAll(Pageable pageable) {
        return jpa.findAll(pageable);
    }
}

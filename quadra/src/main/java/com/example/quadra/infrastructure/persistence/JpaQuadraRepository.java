package com.example.quadra.infrastructure.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.example.quadra.domain.quadra.Quadra;
import com.example.quadra.domain.quadra.QuadraRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaQuadraRepository implements QuadraRepository {
    private final SpringDataQuadraJpa jpa;

    public JpaQuadraRepository(SpringDataQuadraJpa jpa) {
        this.jpa = jpa;
    }

    // @Override
    // public boolean existsByEmail(String email) {
    //     return jpa.existsByEmail_value(email);
    // }

    // @Override
    // public Optional<Quadra> findByEmail(String email) {
    //     return jpa.findByEmail_value(email);
    // }

    @Override
    public Quadra save(Quadra user) {
        return jpa.save(user);
    }

    @Override
    public Optional<Quadra> findById(UUID id) {
        return jpa.findById(id);
    }

    @Override
    public Page<Quadra> findAll(Pageable pageable) {
        return jpa.findAll(pageable);
    }
}

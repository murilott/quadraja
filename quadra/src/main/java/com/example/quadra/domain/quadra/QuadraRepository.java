package com.example.quadra.domain.quadra;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface QuadraRepository {
    boolean existsByEmail(String email);
    Optional<Quadra> findByEmail(String email);
    Quadra save(Quadra quadra);
    Optional<Quadra> findById(UUID id);
    Page<Quadra> findAll(Pageable pageable);
}

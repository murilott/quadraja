package com.example.quadra.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quadra.domain.quadra.Quadra;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataQuadraJpa extends JpaRepository<Quadra, UUID> {
    Optional<Quadra> findByName(String name);
    boolean existsByName(String name);
}

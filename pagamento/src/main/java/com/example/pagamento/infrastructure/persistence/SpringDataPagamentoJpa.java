package com.example.pagamento.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pagamento.domain.pagamento.Pagamento;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataPagamentoJpa extends JpaRepository<Pagamento, UUID> {
    Optional<Pagamento> findByNome(String nome);
    boolean existsByNome(String nome);
}

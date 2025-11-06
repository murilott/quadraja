package com.example.pagamento.domain.pagamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface PagamentoRepository {
    boolean existsByNome(String nome);
    Optional<Pagamento> findByNome(String nome);
    Pagamento save(Pagamento pagamento);
    Optional<Pagamento> findById(UUID id);
    Page<Pagamento> findAll(Pageable pageable);
}

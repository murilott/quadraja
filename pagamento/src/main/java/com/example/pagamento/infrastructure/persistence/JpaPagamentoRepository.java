package com.example.pagamento.infrastructure.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.example.pagamento.domain.pagamento.Pagamento;
import com.example.pagamento.domain.pagamento.PagamentoRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaPagamentoRepository implements PagamentoRepository {
    private final SpringDataPagamentoJpa jpa;

    public JpaPagamentoRepository(SpringDataPagamentoJpa jpa) {
        this.jpa = jpa;
    }

    @Override
    public boolean existsByNome(String nome) {
        return jpa.existsByNome(nome);
    }

    @Override
    public Optional<Pagamento> findByNome(String nome) {
        return jpa.findByNome(nome);
    }

    @Override
    public Pagamento save(Pagamento user) {
        return jpa.save(user);
    }

    @Override
    public Optional<Pagamento> findById(UUID id) {
        return jpa.findById(id);
    }

    @Override
    public Page<Pagamento> findAll(Pageable pageable) {
        return jpa.findAll(pageable);
    }
}

package com.example.pagamento.application.pagamento;

import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.pagamento.domain.pagamento.Pagamento;
import com.example.pagamento.domain.pagamento.PagamentoRepository;
import com.example.pagamento.interfaces.rest.dto.pagamento.PagamentoResponse;

@Service
@RequiredArgsConstructor
public class RegisterPagamentoHandler {
    private final PagamentoRepository pagamentoRepository;

    public PagamentoResponse handle(String nome, String tipo) {
        if (pagamentoRepository.existsByNome(nome)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Pagamento já cadastrado");
        }

        Pagamento pagamento = new Pagamento(nome, tipo);
        Pagamento savedPagamento = pagamentoRepository.save(pagamento);

        return new PagamentoResponse(
                savedPagamento.getId(),
                savedPagamento.getNome(),
                savedPagamento.getTipo()
        );
    }
}

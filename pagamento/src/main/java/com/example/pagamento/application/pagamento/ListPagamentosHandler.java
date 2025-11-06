package com.example.pagamento.application.pagamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.pagamento.domain.pagamento.Pagamento;
import com.example.pagamento.domain.pagamento.PagamentoRepository;
import com.example.pagamento.interfaces.rest.dto.pagamento.PagamentoResponse;

@Service
public class ListPagamentosHandler {
    private final PagamentoRepository pagamentoRepository;

    public ListPagamentosHandler(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    public Page<PagamentoResponse> handle(Pageable pageable) {
        Page<Pagamento> page = pagamentoRepository.findAll(pageable);

        return page.map(pagamento -> new PagamentoResponse(
                pagamento.getId(),
                pagamento.getNome(),
                pagamento.getTipo()
        ));
    }
}

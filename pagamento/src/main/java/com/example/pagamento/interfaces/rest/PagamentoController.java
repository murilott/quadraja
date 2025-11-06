package com.example.pagamento.interfaces.rest;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.pagamento.application.pagamento.ListPagamentosHandler;
import com.example.pagamento.application.pagamento.RegisterPagamentoHandler;
import com.example.pagamento.interfaces.rest.dto.pagamento.PagamentoResponse;
import com.example.pagamento.interfaces.rest.dto.pagamento.RegisterPagamentoRequest;

import java.net.URI;
import java.util.UUID;


@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final ListPagamentosHandler listPagamentosHandler;
    private final RegisterPagamentoHandler registerPagamentoHandler;

    @GetMapping
    public ResponseEntity<Page<PagamentoResponse>> list(Pageable pageable) {
        Page<PagamentoResponse> page = listPagamentosHandler.handle(pageable);

        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<PagamentoResponse> register(@Valid @RequestBody RegisterPagamentoRequest request) {
        PagamentoResponse created = registerPagamentoHandler.handle(request.nome(), request.tipo());

        return ResponseEntity.created(URI.create("/pagamentos/" + created.id())).body(created);
    }
}

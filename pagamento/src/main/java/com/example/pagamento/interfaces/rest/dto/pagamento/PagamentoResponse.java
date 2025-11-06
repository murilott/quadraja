package com.example.pagamento.interfaces.rest.dto.pagamento;

import java.util.UUID;

public record PagamentoResponse(
        UUID id,
        String nome,
        String tipo
    ) {

}

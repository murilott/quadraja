package com.example.authservice.interfaces.rest.dto.pagamento;

import java.util.List;
import java.util.UUID;

public record PagamentoUserResponse(
        String userEmail,
        String nome,
        List<String> pagamentosLista
    ) {

}

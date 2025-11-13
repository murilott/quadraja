package com.example.authservice.interfaces.rest.dto.pagamento;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record RegisterPagamentoToUser (
        @NotBlank String pagamentoNome,
        @NotBlank String usuarioEmail
) {
}

package com.example.reserva.interfaces.rest.dto.reserva;

import java.time.LocalDateTime;

import com.example.reserva.domain.reserva.vo.Periodo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record RegisterReservaRequest(
        @NotBlank String quadraName,
        @NotBlank String usuarioEmail,
        @NotNull LocalDateTime periodo,
        @NotBlank String pagamento
) {
}

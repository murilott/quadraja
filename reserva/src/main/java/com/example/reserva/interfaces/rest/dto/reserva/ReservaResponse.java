package com.example.reserva.interfaces.rest.dto.reserva;

import java.util.UUID;

import com.example.reserva.domain.quadra.Quadra;
import com.example.reserva.domain.reserva.vo.Periodo;
import com.example.reserva.interfaces.rest.dto.quadra.QuadraResponse;

import jakarta.validation.constraints.NotBlank;

public record ReservaResponse(
        UUID id,
        String quadraName,
        String usuarioEmail,
        Periodo periodo,
        String pagamento,
        boolean pago
    ) {

}

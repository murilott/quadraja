package com.example.reserva.interfaces.rest.dto.reserva;

import java.util.UUID;

import com.example.reserva.domain.reserva.vo.Periodo;

public record ReservaResponse(
        UUID id,
        String quadraName,
        Periodo periodo,
        String pagamento,
        boolean pago
    ) {

}

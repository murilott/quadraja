package com.example.reserva.interfaces.rest.dto.reserva;

import java.util.UUID;

import com.example.reserva.domain.quadra.Quadra;
import com.example.reserva.domain.reserva.vo.Periodo;

public record ReservaResponse(
        UUID id,
        Quadra quadra,
        Periodo periodo,
        String pagamento,
        boolean pago
    ) {

}

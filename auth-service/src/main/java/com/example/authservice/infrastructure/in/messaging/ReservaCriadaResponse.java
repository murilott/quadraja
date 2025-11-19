package com.example.authservice.infrastructure.in.messaging;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReservaCriadaResponse(
        UUID id,
        String quadraName,
        String usuarioEmail,
        LocalDateTime periodo,
        String pagamento) {
}

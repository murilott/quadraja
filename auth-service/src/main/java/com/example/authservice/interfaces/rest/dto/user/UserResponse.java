package com.example.authservice.interfaces.rest.dto.user;

import java.util.List;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String email,
        String role,
        List<String> pagamentosLista,
        List<UUID> reservasLista
) {
}

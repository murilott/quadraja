package com.example.reserva.interfaces.rest.dto.quadra;

import java.util.UUID;

import com.example.reserva.domain.quadra.vo.CategoryType;

public record QuadraResponse(
        UUID id,
        String name,
        String local,
        boolean alugado,
        double price,
        CategoryType category
) {}

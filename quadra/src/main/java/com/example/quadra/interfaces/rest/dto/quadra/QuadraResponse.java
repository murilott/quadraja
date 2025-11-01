package com.example.quadra.interfaces.rest.dto.quadra;

import java.util.UUID;

import com.example.quadra.domain.quadra.vo.CategoryType;

public record QuadraResponse(
        UUID id,
        String name,
        String local,
        boolean alugado,
        double price,
        CategoryType category
) {}

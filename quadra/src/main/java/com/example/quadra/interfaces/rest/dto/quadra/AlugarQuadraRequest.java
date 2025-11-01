package com.example.quadra.interfaces.rest.dto.quadra;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlugarQuadraRequest(
    @NotNull String name,
    @NotBlank boolean alugar
) {}

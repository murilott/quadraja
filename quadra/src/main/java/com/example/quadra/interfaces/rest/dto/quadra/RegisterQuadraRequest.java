package com.example.quadra.interfaces.rest.dto.quadra;

import com.example.quadra.domain.quadra.vo.CategoryType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterQuadraRequest(
        @NotBlank String name,
        @NotBlank String local,
        @NotBlank double price,
        @NotBlank CategoryType category
) {
}

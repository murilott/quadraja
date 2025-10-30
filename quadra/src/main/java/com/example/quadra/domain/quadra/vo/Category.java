package com.example.quadra.domain.quadra.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Category {

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false, length = 20)
    private CategoryType value;

    private Category(CategoryType value) {
        if (value == null) {
            throw new IllegalArgumentException("Categoria é obrigatória");
        }

        this.value = value;
    }

    public static Category of(CategoryType value) {
        return new Category(value);
    }

    public boolean covers(CategoryType other) {
        return this.value.covers(other);
    }
}

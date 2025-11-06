package com.example.reserva.domain.quadra.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
public class Price {
    // TODO: adicionar tag de validação para números
    @Column(name = "price")
    private double value;

    public Price() {}

    public Price(double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero");
        }

        this.value = value;
    }

    public static Price of(double value) {
        return new Price(value);
    }

    // private static double normalize(double value) {
    //     return value == null ? null : value.trim().toLowerCase();
    // }
}

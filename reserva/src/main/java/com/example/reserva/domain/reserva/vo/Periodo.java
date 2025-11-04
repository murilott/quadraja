package com.example.reserva.domain.reserva.vo;

import java.time.Instant;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Periodo {
     // TODO: adicionar tag de validação para números
    @Column(name = "periodo")
    private LocalDateTime value;

    public Periodo() {}

    public Periodo(LocalDateTime value) {
        if (value.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("A data não pode ser no passado");
        }

        this.value = value;
    }

    public static Periodo of(LocalDateTime value) {
        return new Periodo(value);
    }

    // private static double normalize(double value) {
    //     return value == null ? null : value.trim().toLowerCase();
    // }
}

package com.example.reserva.domain.pagamento;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

// @Table(name = "pagamento")
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Pagamento {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    // @Valid
    // @Embedded
    private String tipo;

    public Pagamento(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }
}



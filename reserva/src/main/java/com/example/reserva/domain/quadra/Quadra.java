package com.example.reserva.domain.quadra;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

import com.example.reserva.domain.quadra.vo.Category;
import com.example.reserva.domain.quadra.vo.CategoryType;
import com.example.reserva.domain.quadra.vo.Price;

@Table(name = "quadra")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Quadra {
    @Id
    @Column(name = "quadraId", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String local;

    private boolean alugado;

    @Valid
    @Embedded
    private Price price;

    @Embedded
    private Category category;

    public Quadra(String name, String local, boolean alugado, @Valid Price price, CategoryType category) {
        this.name = name;
        this.local = local;
        this.alugado = alugado;
        this.price = price;
        this.category = Category.of(category);
    }
}

package com.example.authservice.domain.user.vo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
public class Pagamentos {
    @Column(name = "pagamentos")
    private List<String> value;

    public void add(String pagamentoValue) {
        if (pagamentoValue == null || pagamentoValue.isBlank()) {
            throw new IllegalArgumentException("O nome do pagamento é obrigatorio");
        }

        if (this.value.contains(pagamentoValue)) {
            throw new IllegalArgumentException("O pagamento já existe na lista");
        }

        this.value.add(pagamentoValue);
    }

    public Pagamentos() {
        this.value = new ArrayList<String>();
    }

    // public Pagamentos(String value) {
    //     if (value == null || value.isBlank()) {
    //         throw new IllegalArgumentException("O nome do pagamento é obrigatorio");
    //     }

    //     this.value = normalize(value);
    // }

    // public static Pagamentos of(String value) {
    //     return new Pagamentos(value);
    // }

    // private static String normalize(String value) {
    //     return value == null ? null : value.trim().toLowerCase();
    // }
}

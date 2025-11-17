package com.example.reserva.domain.reserva;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.example.reserva.domain.quadra.Quadra;
import com.example.reserva.domain.reserva.vo.Periodo;
import com.example.reserva.interfaces.rest.dto.quadra.QuadraResponse;

@Table(name = "reserva")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Reserva {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // @ManyToOne
    private String quadraName;

    private String usuarioEmail;

    @Valid
    @Embedded
    private Periodo periodo;
    
    private String pagamento;

    private boolean pago;

    public Reserva(String quadraName, String usuarioEmail, Periodo periodo, String pagamento) {
        this.quadraName = quadraName;
        this.usuarioEmail = usuarioEmail;
        this.periodo = periodo;
        this.pagamento = pagamento;
    }
}

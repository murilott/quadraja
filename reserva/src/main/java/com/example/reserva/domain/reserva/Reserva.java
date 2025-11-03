package com.example.reserva.domain.reserva;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "quadra")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Reserva {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String quadraName;

    private LocalDateTime periodoReserva;
    
    private String pagamento;

    private boolean pago;

    public Quadra(String quadraName, LocalDateTime periodoReserva, String pagamento, boolean pago) {
        this.quadraName = quadraName;
        this.periodoReserva = periodoReserva;
        this.pagamento = pagamento;
        this.pago = pago;
    }
}

package com.example.reserva.application.reserva;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.reserva.domain.reserva.Reserva;
import com.example.reserva.domain.reserva.ReservaRepository;
import com.example.reserva.domain.reserva.vo.Periodo;
import com.example.reserva.interfaces.rest.dto.reserva.ReservaResponse;

@Service
@RequiredArgsConstructor
public class RegisterReservaHandler {
    private final ReservaRepository reservaRepository;

    public ReservaResponse handle(String quadraName, LocalDateTime periodoRaw, String pagamento) {
        // TODO: Verificar se já existe reserva com mesmo período
        // if (reservaRepository.findAll().stream().anyMatch(r -> r.getPeriodo().equals(periodo))) {
        //     throw new ResponseStatusException(HttpStatus.CONFLICT, "Reserva já cadastrada");
        // }

        Periodo periodo = Periodo.of(periodoRaw);

        Reserva reserva = new Reserva(quadraName, periodo, pagamento);
        Reserva savedReserva = reservaRepository.save(reserva);

        return new ReservaResponse(
                savedReserva.getId(),
                savedReserva.getQuadraName(),
                savedReserva.getPeriodo(),
                savedReserva.getPagamento(),
                savedReserva.isPago()
        );
    }
}

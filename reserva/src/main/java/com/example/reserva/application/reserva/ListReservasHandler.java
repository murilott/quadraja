package com.example.reserva.application.reserva;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.reserva.domain.reserva.Reserva;
import com.example.reserva.domain.reserva.ReservaRepository;
import com.example.reserva.interfaces.rest.dto.reserva.ReservaResponse;

@Service
public class ListReservasHandler {
    private final ReservaRepository quadraRepository;

    public ListReservasHandler(ReservaRepository quadraRepository) {
        this.quadraRepository = quadraRepository;
    }

    public Page<ReservaResponse> handle(Pageable pageable) {
        Page<Reserva> page = quadraRepository.findAll(pageable);

        return page.map(Reserva -> new ReservaResponse(
                Reserva.getId(),
                Reserva.getQuadra(),
                Reserva.getPeriodo(),
                Reserva.getPagamento(),
                Reserva.isPago()
        ));
    }
}

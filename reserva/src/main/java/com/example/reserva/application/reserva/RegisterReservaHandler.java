package com.example.reserva.application.reserva;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.reserva.domain.quadra.Quadra;
import com.example.reserva.domain.quadra.vo.Price;
import com.example.reserva.domain.reserva.Reserva;
import com.example.reserva.domain.reserva.ReservaRepository;
import com.example.reserva.domain.reserva.vo.Periodo;
import com.example.reserva.infrastructure.out.messaging.PagamentoRequestProducer;
import com.example.reserva.infrastructure.out.messaging.QuadraRequestProducer;
import com.example.reserva.infrastructure.out.messaging.UsuarioRequestProducer;
import com.example.reserva.interfaces.rest.dto.quadra.QuadraResponse;
import com.example.reserva.interfaces.rest.dto.reserva.ReservaResponse;

@Service
@RequiredArgsConstructor
public class RegisterReservaHandler {
    private final ReservaRepository reservaRepository;
    private final QuadraRequestProducer quadraRequestProducer;
    private final PagamentoRequestProducer pagamentoRequestProducer;
    private final UsuarioRequestProducer usuarioRequestProducer;

    public ReservaResponse handle(String quadraName, String usuarioEmail, LocalDateTime periodoRaw, String pagamento) {
        // TODO: Verificar se já existe reserva com mesmo período
        // if (reservaRepository.findAll().stream().anyMatch(r -> r.getPeriodo().equals(periodo))) {
        //     throw new ResponseStatusException(HttpStatus.CONFLICT, "Reserva já cadastrada");
        // }
        Periodo periodo = Periodo.of(periodoRaw);

        QuadraResponse quadraResponse = quadraRequestProducer.solicitarQuadra(quadraName);
        String pagamentoNome = pagamentoRequestProducer.solicitarPagamento(pagamento);
        String usuario = usuarioRequestProducer.solicitarUsuario(usuarioEmail, pagamentoNome);

        Reserva reserva = new Reserva(quadraResponse.name(), usuario, periodo, pagamentoNome);
        Reserva savedReserva = reservaRepository.save(reserva);

        return new ReservaResponse(
                savedReserva.getId(),
                savedReserva.getQuadraName(),
                savedReserva.getUsuarioEmail(),
                savedReserva.getPeriodo(),
                savedReserva.getPagamento(),
                savedReserva.isPago()
        );
    }
}

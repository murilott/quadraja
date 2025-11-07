package com.example.quadra.infrastructure.in.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.quadra.domain.quadra.Quadra;
import com.example.quadra.domain.quadra.QuadraRepository;
import com.example.quadra.infrastructure.config.RabbitConfig;
import com.example.quadra.infrastructure.out.messaging.QuadraResponseProducer;
import com.example.quadra.interfaces.rest.dto.quadra.QuadraResponse;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class QuadraRequestListener {

    private final QuadraRepository quadraRepository;
    // private final QuadraResponseProducer responseProducer;

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public QuadraResponse receberSolicitacao(String quadraName) {
        System.out.println("📥 Pedido recebido para Quadra Name: " + quadraName);

        Quadra quadra = quadraRepository.findByName(quadraName)
                .orElseThrow(() -> new NotFoundException("(quadra - receber) Quadra não encontrada: " + quadraName));

        return new QuadraResponse(
                quadra.getId(),
                quadra.getName(),
                quadra.getLocal(),
                quadra.isAlugado(),
                quadra.getPrice().getValue(),
                quadra.getCategory().getValue());
    }
}
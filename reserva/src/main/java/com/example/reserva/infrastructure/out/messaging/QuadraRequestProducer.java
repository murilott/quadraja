package com.example.reserva.infrastructure.out.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.example.reserva.domain.quadra.Quadra;
import com.example.reserva.infrastructure.config.RabbitConfig;
import com.example.reserva.interfaces.rest.dto.quadra.QuadraResponse;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class QuadraRequestProducer {

    private final RabbitTemplate rabbitTemplate;

    public QuadraResponse solicitarQuadra(String quadraName) {
        System.out.println("Solicitando quadra via RabbitMQ: " + quadraName);

        QuadraResponse quadra = (QuadraResponse) rabbitTemplate.convertSendAndReceive(
            RabbitConfig.EXCHANGE_NAME,  // exchange
            RabbitConfig.ROUTING_KEY,       // routing key
            quadraName              // mensagem (nome)
        );

        if (quadra == null) {
            throw new RuntimeException("(reserva - solicitar) Quadra não encontrada: " + quadraName);
        }

        System.out.println("Recebida resposta da quadra: " + quadra.name());
        return quadra;
    }
}
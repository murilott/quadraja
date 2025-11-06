package com.example.quadra.infrastructure.out.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.example.quadra.domain.quadra.Quadra;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class QuadraResponseProducer {

    private final RabbitTemplate rabbitTemplate;

    public void enviarResposta(Quadra quadra) {
        rabbitTemplate.convertAndSend("quadra.exchange", "quadra.response", quadra);

        System.out.println("📤 Resposta de quadra enviada: " + quadra.getName());
    }
}
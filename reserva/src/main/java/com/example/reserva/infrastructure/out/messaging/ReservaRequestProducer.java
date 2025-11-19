package com.example.reserva.infrastructure.out.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.reserva.infrastructure.config.RabbitConfig;
import com.example.reserva.infrastructure.in.messaging.ReservaCriadaResponse;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ReservaRequestProducer {
    private final RabbitTemplate template;

    public void publish(ReservaCriadaResponse event) {
        System.out.println("Enviando reserva para auth-service via RabbitMQ: " + event);

        template.convertAndSend(
            RabbitConfig.EXCHANGE_NAME,
            RabbitConfig.ROUTING_KEY_NOTIFICATION,
            event
        );
    }

}
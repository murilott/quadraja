package com.example.reserva.infrastructure.out.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.example.reserva.domain.quadra.Quadra;

@Component
public class QuadraResponseListener {

    @RabbitListener(queues = "quadra.response.queue")
    public void receberQuadra(Quadra quadra) {
        System.out.println("📥 Recebida resposta da quadra: " + quadra.getName());
    }
}
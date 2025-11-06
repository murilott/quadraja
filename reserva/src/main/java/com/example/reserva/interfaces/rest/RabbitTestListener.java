package com.example.reserva.interfaces.rest;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitTestListener {

    @RabbitListener(queues = "test-queue")
    public void receiveMessage(String message) {
        System.out.println("📩 Recebido via RabbitMQ: " + message);
    }
}

package com.example.reserva.interfaces.rest;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue testQueue() {
        // Cria a fila se não existir
        return new Queue("test-queue", true);
    }
}

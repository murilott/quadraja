package com.example.reserva.infrastructure.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String EXCHANGE_NAME = "quadra.rpc.exchange";
    public static final String QUEUE_NAME = "quadra.rpc.queue";
    public static final String ROUTING_KEY = "quadra.rpc.key";

    @Bean
    public DirectExchange quadraExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue quadraQueue() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public Binding quadraBinding(Queue quadraQueue, DirectExchange quadraExchange) {
        return BindingBuilder.bind(quadraQueue).to(quadraExchange).with(ROUTING_KEY);
    }
}

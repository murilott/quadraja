package com.example.reserva.infrastructure.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String EXCHANGE_NAME = "quadra.rpc.exchange";
    public static final String QUEUE_NAME = "quadra.response.queue";
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

    // @Bean
    // public MessageConverter jsonMessageConverter() {
    //     return new Jackson2JsonMessageConverter();
    // }

    @Bean
    public MessageConverter jsonMessageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        DefaultClassMapper classMapper = new DefaultClassMapper();
        classMapper.setTrustedPackages("*"); // Permite desserializar qualquer pacote
        converter.setClassMapper(classMapper);
        return converter;
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}

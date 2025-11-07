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
    public static final String ROUTING_KEY = "quadra.rpc.key";

    @Bean
    public DirectExchange quadraExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    // @Bean
    // public MessageConverter jsonMessageConverter() {
    // Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
    // DefaultClassMapper classMapper = new DefaultClassMapper();
    // classMapper.setTrustedPackages("*");
    // converter.setClassMapper(classMapper);
    // return converter;
    // }

    @Bean
    public MessageConverter jsonMessageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        DefaultClassMapper classMapper = new DefaultClassMapper();

        // Ignora o tipo Java original enviado pelo produtor
        classMapper.setTrustedPackages("*");
        classMapper.setDefaultType(java.util.LinkedHashMap.class);

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

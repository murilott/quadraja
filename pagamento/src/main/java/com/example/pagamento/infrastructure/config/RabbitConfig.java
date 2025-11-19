package com.example.pagamento.infrastructure.config;

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
    public static final String EXCHANGE_NAME = "quadraja.exchange";
    
    // reserva
    public static final String QUEUE_NAME = "pagamento.queue";
    public static final String ROUTING_KEY = "pagamento.key";

    // auth
    public static final String QUEUE_NAME_USER = "pagamento.user.queue";
    public static final String ROUTING_KEY_USER = "pagamento.user.key";

    @Bean
    public DirectExchange pagamentoExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue pagamentoQueue() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public Queue pagamentoUserQueue() {
        return new Queue(QUEUE_NAME_USER);
    }

    @Bean
    public Binding pagamentoBinding(Queue pagamentoQueue, DirectExchange pagamentoExchange) {
        return BindingBuilder.bind(pagamentoQueue).to(pagamentoExchange).with(ROUTING_KEY);
    }

    @Bean
    public Binding pagamentoUserBinding(Queue pagamentoUserQueue, DirectExchange pagamentoExchange) {
        return BindingBuilder.bind(pagamentoUserQueue).to(pagamentoExchange).with(ROUTING_KEY_USER);
    }

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

package com.example.authservice.infrastructure.config;

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
    public static final String EXCHANGE_NAME = "user.rpc.exchange";
    public static final String ROUTING_KEY = "user.rpc.key";
    public static final String QUEUE_NAME = "user.rpc.queue";

    public static final String QUEUE_NAME_PAGAMENTO = "pagamento.user.rpc.queue";
    public static final String ROUTING_KEY_PAGAMENTO = "pagamento.user.rpc.key";
    public static final String EXCHANGE_NAME_PAGAMENTO = "pagamento.rpc.exchange";

    @Bean
    public DirectExchange userExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    // @Bean
    // public DirectExchange userPagamentoExchange() {
    //     return new DirectExchange(EXCHANGE_NAME);
    // }

    @Bean
    public Queue userQueue() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public Binding userBinding(Queue userQueue, DirectExchange userExchange) {
        return BindingBuilder.bind(userQueue).to(userExchange).with(ROUTING_KEY);
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

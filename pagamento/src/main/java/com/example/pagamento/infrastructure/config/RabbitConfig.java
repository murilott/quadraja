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
    public static final String EXCHANGE_NAME = "pagamento.rpc.exchange";
    public static final String QUEUE_NAME = "pagamento.rpc.queue";
    public static final String QUEUE_NAME_USER = "pagamento.user.rpc.queue";
    public static final String ROUTING_KEY = "pagamento.rpc.key";
    public static final String ROUTING_KEY_USER = "pagamento.user.rpc.key";

    // public static final String EXCHANGE_NAME_USER = "user.rpc.exchange";
    // public static final String ROUTING_KEY_USER = "user.rpc.key";

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

    // @Bean
    // public DirectExchange userExchange() {
    //     return new DirectExchange(EXCHANGE_NAME_USER);
    // }

    // @Bean
    // public Queue userQueue() {
    //     return new Queue(QUEUE_NAME_USER);
    // }

    @Bean
    public Binding pagamentoBinding(Queue pagamentoQueue, DirectExchange pagamentoExchange) {
        return BindingBuilder.bind(pagamentoQueue).to(pagamentoExchange).with(ROUTING_KEY);
    }

    @Bean
    public Binding pagamentoUserBinding(Queue pagamentoUserQueue, DirectExchange pagamentoExchange) {
        return BindingBuilder.bind(pagamentoUserQueue).to(pagamentoExchange).with(ROUTING_KEY_USER);
    }

    // @Bean
    // public Binding userBinding(Queue userQueue, DirectExchange userExchange) {
    //     return BindingBuilder.bind(userQueue).to(userExchange).with(ROUTING_KEY);
    // }

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

package com.example.authservice.infrastructure.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
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

    public static final String ROUTING_KEY = "user.key";
    public static final String QUEUE_NAME = "user.queue";

    public static final String QUEUE_NAME_PAGAMENTO = "pagamento.user.queue";
    public static final String ROUTING_KEY_PAGAMENTO = "pagamento.user.key";
    
    public static final String NOTIFICATION_QUEUE_NAME = "notification";
    public static final String ROUTING_KEY_NOTIFICATION = "notification.key";

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue userQueue() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public Queue pagamentoQueue() {
        return new Queue(QUEUE_NAME_PAGAMENTO);
    }

    @Bean
    public Queue notificationQueue() {
        return new Queue(NOTIFICATION_QUEUE_NAME);
    }

    @Bean
    public Binding userBinding(Queue userQueue, DirectExchange exchange) {
        return BindingBuilder.bind(userQueue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    public Binding pagamentoBinding(Queue pagamentoQueue, DirectExchange exchange) {
        return BindingBuilder.bind(pagamentoQueue).to(exchange).with(ROUTING_KEY_PAGAMENTO);
    }

    @Bean
    public Binding notificationBinding(Queue notificationQueue, DirectExchange exchange) {
        return BindingBuilder.bind(notificationQueue).to(exchange).with(ROUTING_KEY_NOTIFICATION);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        return converter;
    }
    
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    SimpleRabbitListenerContainerFactory container(
        ConnectionFactory connectionFactory,
        Jackson2JsonMessageConverter messageConverter
    ) {
        SimpleRabbitListenerContainerFactory containerFactory = new SimpleRabbitListenerContainerFactory();
        containerFactory.setConnectionFactory(connectionFactory);
        containerFactory.setMessageConverter(messageConverter);

        return containerFactory;
    }
}

package com.example.reserva.infrastructure.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String EXCHANGE_NAME = "quadraja.exchange";

    // public static final String EXCHANGE_NAME_QUADRA = "quadra.rpc.exchange";
    public static final String ROUTING_KEY_QUADRA = "quadra.key";
    
    // public static final String EXCHANGE_NAME_PAGAMENTO = "pagamento.rpc.exchange";
    public static final String ROUTING_KEY_PAGAMENTO = "pagamento.key";

    // public static final String EXCHANGE_NAME_USUARIO = "user.rpc.exchange";
    public static final String ROUTING_KEY_USUARIO = "user.key";

    // public static final String EXCHANGE_NAME_NOTIFICATION = "notification.exchange";
    public static final String ROUTING_KEY_NOTIFICATION = "notification.key";



    @Bean
    public DirectExchange quadraExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();

        // Ignora o tipo Java original enviado pelo produtor
        typeMapper.setTrustedPackages("*");
        
        converter.setJavaTypeMapper(typeMapper);
        converter.setClassMapper(typeMapper);
        return converter;
    }

    // @Bean
    // public MessageConverter jsonMessageConverter() {
    //     Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
    //     DefaultClassMapper classMapper = new DefaultClassMapper();

    //     // Ignora o tipo Java original enviado pelo produtor
    //     classMapper.setTrustedPackages("*");
    //     classMapper.setDefaultType(java.util.LinkedHashMap.class);

    //     converter.setClassMapper(classMapper);
    //     return converter;
    // }

    @Bean
    RabbitTemplate rabbitTemplate(
        ConnectionFactory connectionFactory,
        Jackson2JsonMessageConverter messageConverter
        ) {
            RabbitTemplate template = new RabbitTemplate(connectionFactory);
            template.setMessageConverter(messageConverter);

            return template;
        }
}

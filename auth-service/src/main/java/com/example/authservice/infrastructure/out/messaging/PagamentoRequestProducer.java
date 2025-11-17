package com.example.authservice.infrastructure.out.messaging;

import java.util.Map;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.example.authservice.infrastructure.config.RabbitConfig;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PagamentoRequestProducer {

    private final RabbitTemplate rabbitTemplate;

    public String solicitarPagamento(String pagamentoNome) {
        System.out.println("Solicitando pagamento via RabbitMQ: " + pagamentoNome);

        Object response = rabbitTemplate.convertSendAndReceive(
                RabbitConfig.EXCHANGE_NAME_PAGAMENTO, // exchange
                RabbitConfig.ROUTING_KEY_PAGAMENTO, // routing key
                pagamentoNome // mensagem (nome)
        );

        if (response == null) {
            throw new RuntimeException("(auth-service - solicitar) Pagamento não encontrado: " + pagamentoNome);
        }

        // System.out.println("Recebida resposta da pagamento: " + pagamento.name());
        // return pagamento;
        
        return response.toString();
    }
}
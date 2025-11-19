package com.example.reserva.infrastructure.out.messaging;

import java.util.Map;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.example.reserva.domain.pagamento.Pagamento;
import com.example.reserva.infrastructure.config.RabbitConfig;
import com.example.reserva.interfaces.rest.dto.pagamento.PagamentoResponse;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PagamentoRequestProducer {

    private final RabbitTemplate rabbitTemplate;

    public String solicitarPagamento(String pagamentoName) {
        System.out.println("Solicitando pagamento via RabbitMQ: " + pagamentoName);

        Object response = rabbitTemplate.convertSendAndReceive(
                RabbitConfig.EXCHANGE_NAME, // exchange
                RabbitConfig.ROUTING_KEY_PAGAMENTO, // routing key
                pagamentoName // mensagem (nome)
        );

        if (response == null) {
            throw new RuntimeException("(reserva - solicitar) Pagamento não encontrada: " + pagamentoName);
        }

        // System.out.println("Recebida resposta da pagamento: " + pagamento.name());
        // return pagamento;
        
        return response.toString();
    }
}
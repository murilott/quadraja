package com.example.reserva.infrastructure.out.messaging;

import java.util.Map;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.example.reserva.infrastructure.config.RabbitConfig;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UsuarioRequestProducer {

    private final RabbitTemplate rabbitTemplate;

    public String solicitarUsuario(String usuarioEmail, String pagamento) {
        System.out.println("Solicitando usuário e pagamento via RabbitMQ: " + usuarioEmail + " " + pagamento);
        Object[] carga = {usuarioEmail, pagamento};

        Object response = rabbitTemplate.convertSendAndReceive(
                RabbitConfig.EXCHANGE_NAME_USUARIO, // exchange
                RabbitConfig.ROUTING_KEY_USUARIO, // routing key
                carga // mensagem (nome)
        );

        if (response == null) {
            throw new RuntimeException("(reserva - solicitar) Usuario ou pagamento não encontrada: " + usuarioEmail + " "  + pagamento);
        }

        return response.toString();
    }
}
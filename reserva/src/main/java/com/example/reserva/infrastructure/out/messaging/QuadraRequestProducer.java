package com.example.reserva.infrastructure.out.messaging;

import java.util.Map;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.example.reserva.domain.quadra.Quadra;
import com.example.reserva.domain.quadra.vo.CategoryType;
import com.example.reserva.infrastructure.config.RabbitConfig;
import com.example.reserva.interfaces.rest.dto.quadra.QuadraResponse;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class QuadraRequestProducer {

    private final RabbitTemplate rabbitTemplate;

    public QuadraResponse solicitarQuadra(String quadraName) {
        System.out.println("Solicitando quadra via RabbitMQ: " + quadraName);

        Object response = rabbitTemplate.convertSendAndReceive(
                RabbitConfig.EXCHANGE_NAME_QUADRA, // exchange
                RabbitConfig.ROUTING_KEY_QUADRA, // routing key
                quadraName // mensagem (nome)
        );

        if (response == null) {
            throw new RuntimeException("(reserva - solicitar) Quadra não encontrada: " + quadraName);
        }

        // System.out.println("Recebida resposta da quadra: " + quadra.name());
        // return quadra;
        
        if (response instanceof Map<?, ?> map) {
            // Converte os valores corretamente respeitando os tipos do DTO
            UUID id = map.get("id") != null ? UUID.fromString(map.get("id").toString()) : null;
            String name = (String) map.get("name");
            String local = (String) map.get("local");
            boolean alugado = map.get("alugado") != null && Boolean.parseBoolean(map.get("alugado").toString());
            double price = map.get("price") != null ? Double.parseDouble(map.get("price").toString()) : 0.0;

            CategoryType category = null;
            if (map.get("category") != null) {
                try {
                    category = CategoryType.valueOf(map.get("category").toString());
                } catch (IllegalArgumentException e) {
                    System.err.println("⚠️ Categoria inválida recebida: " + map.get("category"));
                }
            }

            QuadraResponse quadra = new QuadraResponse(id, name, local, alugado, price, category);

            System.out.println("✅ Recebida resposta da quadra: " + quadra.name());
            return quadra;
        }

        throw new RuntimeException("Resposta inesperada do serviço Quadra: " + response);
    }
}
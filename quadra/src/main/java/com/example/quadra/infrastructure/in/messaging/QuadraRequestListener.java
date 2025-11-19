package com.example.quadra.infrastructure.in.messaging;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.quadra.domain.quadra.Quadra;
import com.example.quadra.domain.quadra.QuadraRepository;
import com.example.quadra.infrastructure.config.RabbitConfig;
import com.example.quadra.interfaces.rest.dto.quadra.QuadraResponse;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class QuadraRequestListener {

    private final QuadraRepository quadraRepository;
    // private final QuadraResponseProducer responseProducer;

    // @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    // public Map<String, Object> receberSolicitacao(String quadraName) {
    //     try {
    //         System.out.println("📥 Pedido recebido para Quadra Name: " + quadraName);

    //         Quadra quadra = quadraRepository.findByName(quadraName)
    //                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
    //                         "(quadra - receber) Quadra não encontrada: " + quadraName));

    //         // return new QuadraResponse(
    //         // quadra.getId(),
    //         // quadra.getName(),
    //         // quadra.getLocal(),
    //         // quadra.isAlugado(),
    //         // quadra.getPrice().getValue(),
    //         // quadra.getCategory().getValue());

    //         return Map.of(
    //                 "id", quadra.getId().toString(),
    //                 "name", quadra.getName(),
    //                 "local", quadra.getLocal(),
    //                 "alugado", quadra.isAlugado(),
    //                 "price", quadra.getPrice().getValue(),
    //                 "category", quadra.getCategory().getValue().name());
    //     } catch (Exception e) {
    //         System.err.println("⚠️ Erro ao processar mensagem RabbitMQ: " + e.getMessage());

    //         return null;
    //     }
    // }

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public String receberSolicitacao(String quadraName) {
        try {
            System.out.println("📥 Pedido recebido para Quadra Name: " + quadraName);

            Quadra quadra = quadraRepository.findByName(quadraName)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "(quadra - receber) Quadra não encontrada: " + quadraName));

            // return new QuadraResponse(
            // quadra.getId(),
            // quadra.getName(),
            // quadra.getLocal(),
            // quadra.isAlugado(),
            // quadra.getPrice().getValue(),
            // quadra.getCategory().getValue());

            return quadra.getName();
        } catch (Exception e) {
            System.err.println("⚠️ Erro ao processar mensagem RabbitMQ: " + e.getMessage());

            return null;
        }
    }
}
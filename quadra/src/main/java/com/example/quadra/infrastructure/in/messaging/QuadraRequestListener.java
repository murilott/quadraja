package com.example.quadra.infrastructure.in.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.quadra.domain.quadra.Quadra;
import com.example.quadra.domain.quadra.QuadraRepository;
import com.example.quadra.infrastructure.out.messaging.QuadraResponseProducer;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class QuadraRequestListener {

    private final QuadraRepository quadraRepository;
    // private final QuadraResponseProducer responseProducer;

    @RabbitListener(queues = "quadra.rpc.queue")
    public Quadra receberSolicitacao(String quadraName) {
        System.out.println("📥 Pedido recebido para Quadra Name: " + quadraName);

        return quadraRepository.findByName(quadraName)
            .orElseThrow(() -> new NotFoundException("Quadra não encontrada: " + quadraName));
    }
}
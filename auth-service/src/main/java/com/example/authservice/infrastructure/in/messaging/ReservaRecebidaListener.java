package com.example.authservice.infrastructure.in.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.authservice.infrastructure.config.RabbitConfig;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReservaRecebidaListener {
    
    @RabbitListener(queues = RabbitConfig.NOTIFICATION_QUEUE_NAME)
    public void reservaCriada(ReservaCriadaResponse reserva) {
        try {
            String usuarioEmail = reserva.getUsuarioEmail();
            
            User user = userRepository.findByEmail(usuarioEmail).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email inválido ou usuário não cadastrado")
            );

            System.out.println("📥 Reserva criada recebida: " + reserva);
        } catch (Exception e) {
            System.err.println("⚠️ Erro ao processar mensagem RabbitMQ: " + e.getMessage());
        }
    }
}

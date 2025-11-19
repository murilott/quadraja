package com.example.authservice.infrastructure.in.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.authservice.domain.user.User;
import com.example.authservice.domain.user.UserRepository;
import com.example.authservice.infrastructure.config.RabbitConfig;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReservaRecebidaListener {
    private final UserRepository userRepository;
    
    @RabbitListener(queues = RabbitConfig.NOTIFICATION_QUEUE_NAME)
    public void reservaCriada(ReservaCriadaResponse reserva) {
        try {
            System.out.println("📥 Reserva criada recebida: " + reserva);

            String usuarioEmail = reserva.usuarioEmail();
            
            User user = userRepository.findByEmail(usuarioEmail).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email inválido ou usuário não cadastrado")
            );

            user.getReservasLista().add(reserva.id());
            userRepository.save(user);

            System.out.println("Reserva " + reserva.id() + " adicionada ao usuário " + usuarioEmail);
        } catch (Exception e) {
            System.err.println("⚠️ Erro ao processar mensagem RabbitMQ: " + e.getMessage());
        }
    }
}

package com.example.authservice.infrastructure.in.messaging;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.authservice.domain.user.User;
import com.example.authservice.domain.user.UserRepository;
import com.example.authservice.infrastructure.config.RabbitConfig;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UsuarioRequestListener {

    private final UserRepository userRepository;
    // private final UserResponseProducer responseProducer;

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public String receberSolicitacao(Object[] mensagem) {
        try {
            String userEmail = (String) mensagem[0];
            String pagamento = (String) mensagem[1];
            System.out.println("📥 Pedido recebido para User Name: " + userEmail);

            User user = userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "(user - receber) User não encontrado: " + userEmail));

            if (!user.getPagamentosLista().contains(pagamento)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "(user - receber) Pagamento não associado ao usuário: " + pagamento);
            }
            
            return user.getEmail().getValue();
        } catch (Exception e) {
            System.err.println("⚠️ Erro ao processar mensagem RabbitMQ: " + e.getMessage());

            return null;
        }
    }
}
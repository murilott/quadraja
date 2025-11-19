package com.example.pagamento.infrastructure.in.messaging;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.pagamento.domain.pagamento.Pagamento;
import com.example.pagamento.domain.pagamento.PagamentoRepository;
import com.example.pagamento.infrastructure.config.RabbitConfig;
import com.example.pagamento.interfaces.rest.dto.pagamento.PagamentoResponse;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PagamentoRequestListener {

    private final PagamentoRepository pagamentoRepository;
    // private final PagamentoResponseProducer responseProducer;

    // reserva
    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public String receberSolicitacao(String pagamentoName) {
        try {
            System.out.println("📥 Pedido recebido para Pagamento Name: " + pagamentoName);

            Pagamento pagamento = pagamentoRepository.findByNome(pagamentoName)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "(pagamento - receber) Pagamento não encontrado: " + pagamentoName));

            return pagamento.getNome();
        } catch (Exception e) {
            System.err.println("⚠️ Erro ao processar mensagem RabbitMQ: " + e.getMessage());

            return null;
        }
    }

    // auth-service
    @RabbitListener(queues = RabbitConfig.QUEUE_NAME_USER)
    public String receberSolicitacaoUser(String pagamentoName) {
        try {
            System.out.println("📥 (User) Pedido recebido para Pagamento Name: " + pagamentoName);

            Pagamento pagamento = pagamentoRepository.findByNome(pagamentoName)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "(User) (pagamento - receber) Pagamento não encontrado: " + pagamentoName));

            return pagamento.getNome();
        } catch (Exception e) {
            System.err.println("⚠️ (User) Erro ao processar mensagem RabbitMQ: " + e.getMessage());

            return null;
        }
    }
}
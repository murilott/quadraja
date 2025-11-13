package com.example.authservice.application.user;

import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.authservice.domain.user.User;
import com.example.authservice.domain.user.UserRepository;
import com.example.authservice.infrastructure.out.messaging.PagamentoRequestProducer;
import com.example.authservice.interfaces.rest.dto.pagamento.PagamentoUserResponse;

@Service
@RequiredArgsConstructor
public class RegisterPagamentoUserHandler {
    private final UserRepository userRepository;
    private final PagamentoRequestProducer pagamentoRequestProducer;

    public PagamentoUserResponse handle(String pagamentoNome, String usuarioEmail) {
        User user = userRepository.findByEmail(usuarioEmail).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email inválido ou usuário não cadastrado")
        );

        // TODO: acessar usuario pelo rabbit
        String pagamento = pagamentoRequestProducer.solicitarPagamento(pagamentoNome);

        user.getPagamentosLista().add(pagamento);
        User savedUser = userRepository.save(user);

        return new PagamentoUserResponse(
                savedUser.getEmail().getValue(),
                pagamento,
                savedUser.getPagamentosLista()
        );
    }
}

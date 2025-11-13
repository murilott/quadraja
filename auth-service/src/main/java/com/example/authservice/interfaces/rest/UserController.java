package com.example.authservice.interfaces.rest;


import com.example.authservice.application.user.ListUsersHandler;
import com.example.authservice.application.user.RegisterPagamentoUserHandler;
import com.example.authservice.application.user.RegisterUserHandler;
import com.example.authservice.interfaces.rest.dto.pagamento.PagamentoUserResponse;
import com.example.authservice.interfaces.rest.dto.pagamento.RegisterPagamentoToUser;
import com.example.authservice.interfaces.rest.dto.user.RegisterUserRequest;
import com.example.authservice.interfaces.rest.dto.user.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final ListUsersHandler listUsersHandler;
    private final RegisterUserHandler registerUserHandler;
    private final RegisterPagamentoUserHandler registerPagamentoToUserHandler;

    @GetMapping
    public ResponseEntity<Page<UserResponse>> list(Pageable pageable) {
        Page<UserResponse> page = listUsersHandler.handle(pageable);

        return ResponseEntity.ok(page);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterUserRequest request) {
        UserResponse created = registerUserHandler.handle(request.name(), request.email(), request.password());

        return ResponseEntity.created(URI.create("/users/" + created.id())).body(created);
    }

    @PostMapping("/pagamento")
    public ResponseEntity<PagamentoUserResponse> addPagamento(@Valid @RequestBody RegisterPagamentoToUser request) {
        PagamentoUserResponse created = registerPagamentoToUserHandler.handle(request.pagamentoNome(), request.usuarioEmail());

        return ResponseEntity.ok(created);
    }
}

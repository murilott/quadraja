package com.example.reserva.interfaces.rest;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.reserva.interfaces.rest.dto.reserva.ReservaResponse;
import com.example.reserva.application.reserva.ListReservasHandler;
import com.example.reserva.application.reserva.RegisterReservaHandler;
import com.example.reserva.interfaces.rest.dto.reserva.RegisterReservaRequest;

import java.net.URI;
import java.util.UUID;


@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ListReservasHandler listReservasHandler;
    private final RegisterReservaHandler registerReservaHandler;

    @GetMapping
    public ResponseEntity<Page<ReservaResponse>> list(Pageable pageable) {
        Page<ReservaResponse> page = listReservasHandler.handle(pageable);

        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<ReservaResponse> register(@Valid @RequestBody RegisterReservaRequest request) {
        ReservaResponse created = registerReservaHandler.handle(request.quadraName(), request.usuarioEmail(), request.periodo(), request.pagamento());

        return ResponseEntity.created(URI.create("/reservas/" + created.id())).body(created);
    }
    
}

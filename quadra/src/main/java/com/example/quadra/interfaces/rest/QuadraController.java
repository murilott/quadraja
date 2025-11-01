package com.example.quadra.interfaces.rest;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.quadra.application.quadra.quadra.ListQuadrasHandler;
import com.example.quadra.application.quadra.quadra.RegisterQuadraHandler;
import com.example.quadra.interfaces.rest.dto.quadra.QuadraResponse;
import com.example.quadra.interfaces.rest.dto.quadra.RegisterQuadraRequest;

import java.net.URI;

@RestController
@RequestMapping("/quadras")
@RequiredArgsConstructor
public class QuadraController {

    private final ListQuadrasHandler listQuadrasHandler;
    private final RegisterQuadraHandler registerQuadraHandler;

    @GetMapping
    public ResponseEntity<Page<QuadraResponse>> list(Pageable pageable) {
        Page<QuadraResponse> page = listQuadrasHandler.handle(pageable);

        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<QuadraResponse> register(@Valid @RequestBody RegisterQuadraRequest request) {
        QuadraResponse created =registerQuadraHandler.handle(request.name(), request.local(), request.price(), request.category());

        return ResponseEntity.created(URI.create("/quadras/" + created.id())).body(created);
    }
}

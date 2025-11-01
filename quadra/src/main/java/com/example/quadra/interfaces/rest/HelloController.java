package com.example.quadra.interfaces.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "Serviço de quadra. Rotas: GET /quadras: lista; POST /quadras: registrar quadra";
    }

    @GetMapping("/quadra")
    public String hello() {
        return "Serviço de quadra";
    }
}

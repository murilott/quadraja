package com.example.authservice.interfaces.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/quadra")
    public String hello() {
        return "Serviço de quadra";
    }
}

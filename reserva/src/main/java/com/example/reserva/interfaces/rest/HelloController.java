package com.example.reserva.interfaces.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/reserva")
    public String hello() {
        return "Serviço de reserva";
    }
}

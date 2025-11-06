package com.example.pagamento.interfaces.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/pagamento")
    public String hello() {
        return "Serviço de pagamento";
    }
}

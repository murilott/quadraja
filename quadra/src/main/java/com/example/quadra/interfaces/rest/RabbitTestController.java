package com.example.quadra.interfaces.rest;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class RabbitTestController {

    private final RabbitTemplate rabbitTemplate;

    public RabbitTestController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/send")
    public String sendMessage() {
        rabbitTemplate.convertAndSend("test-queue", "Mensagem de teste do serviço Quadra!");
        return "Mensagem enviada para a fila!";
    }
}

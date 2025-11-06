package com.example.chatreactivo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/")
    public Mono<Map<String, String>> healthCheck() {
        return Mono.just(Map.of(
                "status", "API OK",
                "message", "Servicio Reactivo de Chat funcionando."
        ));
    }
}
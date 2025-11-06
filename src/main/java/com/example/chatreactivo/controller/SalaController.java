package com.example.chatreactivo.controller;

import com.example.chatreactivo.entity.Sala;
import com.example.chatreactivo.service.SalaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/salas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SalaController {

    private final SalaService salaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Sala> crear(@RequestBody Sala sala) {
        return salaService.crearSala(sala);
    }

    @GetMapping("/{id}")
    public Mono<Sala> obtenerPorId(@PathVariable Long id) {
        return salaService.obtenerPorId(id);
    }

    @GetMapping
    public Flux<Sala> obtenerTodas() {
        return salaService.obtenerTodas();
    }

    @GetMapping("/publicas")
    public Flux<Sala> obtenerPublicas() {
        return salaService.obtenerPublicas();
    }

    @GetMapping("/creador/{creadorId}")
    public Flux<Sala> obtenerPorCreador(@PathVariable Long creadorId) {
        return salaService.obtenerPorCreador(creadorId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> eliminar(@PathVariable Long id) {
        return salaService.eliminar(id);
    }
}
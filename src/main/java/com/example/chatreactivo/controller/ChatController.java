package com.example.chatreactivo.controller;

import com.example.chatreactivo.entity.Mensaje;
import com.example.chatreactivo.service.MensajeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ChatController {

    private final MensajeService mensajeService;

    @PostMapping("/mensajes")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Mensaje> enviarMensaje(@RequestBody Mensaje mensaje) {
        return mensajeService.enviarMensaje(mensaje);
    }

    @GetMapping("/sala/{salaId}/mensajes")
    public Flux<Mensaje> obtenerMensajes(@PathVariable Long salaId) {
        return mensajeService.obtenerMensajesDeSala(salaId);
    }

    @GetMapping("/sala/{salaId}/mensajes/no-leidos")
    public Flux<Mensaje> obtenerMensajesNoLeidos(@PathVariable Long salaId) {
        return mensajeService.obtenerMensajesNoLeidos(salaId);
    }

    // Endpoint de streaming para recibir mensajes en tiempo real
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Mensaje> streamMensajes() {
        return mensajeService.streamMensajes();
    }

    // Stream de mensajes de una sala específica
    @GetMapping(value = "/sala/{salaId}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Mensaje> streamMensajesDeSala(@PathVariable Long salaId) {
        return mensajeService.streamMensajesDeSala(salaId);
    }

    @PutMapping("/mensajes/{id}/leido/{lectorId}")
    public Mono<Mensaje> marcarLeido(@PathVariable Long id, @PathVariable Long lectorId) {
        return mensajeService.marcarComoLeido(id, lectorId);
    }
}

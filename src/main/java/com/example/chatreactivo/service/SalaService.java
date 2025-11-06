package com.example.chatreactivo.service;

import com.example.chatreactivo.entity.Sala;
import com.example.chatreactivo.repository.SalaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SalaService {

    private final SalaRepository salaRepository;

    public Mono<Sala> crearSala(Sala sala) {
        sala.setFechaCreacion(LocalDateTime.now());
        return salaRepository.save(sala);
    }

    public Mono<Sala> obtenerPorId(Long id) {
        return salaRepository.findById(id);
    }

    public Flux<Sala> obtenerTodas() {
        return salaRepository.findAll();
    }

    public Flux<Sala> obtenerPublicas() {
        return salaRepository.findByPublica(true);
    }

    public Flux<Sala> obtenerPorCreador(Long creadorId) {
        return salaRepository.findByCreadorId(creadorId);
    }

    public Mono<Void> eliminar(Long id) {
        return salaRepository.deleteById(id);
    }
}
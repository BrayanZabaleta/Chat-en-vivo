package com.example.chatreactivo.repository;

import com.example.chatreactivo.entity.Sala;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface SalaRepository extends R2dbcRepository<Sala, Long> {

    Flux<Sala> findByPublica(Boolean publica);

    Flux<Sala> findByCreadorId(Long creadorId);
}
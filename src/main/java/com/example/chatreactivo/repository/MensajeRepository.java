package com.example.chatreactivo.repository;

import com.example.chatreactivo.entity.Mensaje;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MensajeRepository extends R2dbcRepository<Mensaje, Long> {

    Flux<Mensaje> findBySalaIdOrderByFechaEnvioDesc(Long salaId);

    Flux<Mensaje> findByUsuarioId(Long usuarioId);

    Flux<Mensaje> findBySalaIdAndLeido(Long salaId, Boolean leido);
}
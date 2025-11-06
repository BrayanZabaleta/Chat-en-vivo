package com.example.chatreactivo.service;


import com.example.chatreactivo.entity.Mensaje;
import com.example.chatreactivo.repository.MensajeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MensajeService {

    private final MensajeRepository mensajeRepository;

    // Sink para transmitir mensajes en tiempo real
    private final Sinks.Many<Mensaje> mensajeSink = Sinks.many().multicast().onBackpressureBuffer();

    public Mono<Mensaje> enviarMensaje(Mensaje mensaje) {
        mensaje.setFechaEnvio(LocalDateTime.now());
        mensaje.setLeido(false);

        return mensajeRepository.save(mensaje)
                .doOnSuccess(mensajeGuardado -> {
                    // Emitir el mensaje a todos los suscriptores
                    mensajeSink.tryEmitNext(mensajeGuardado);
                });
    }

    public Flux<Mensaje> obtenerMensajesDeSala(Long salaId) {
        return mensajeRepository.findBySalaIdOrderByFechaEnvioDesc(salaId);
    }

    public Flux<Mensaje> obtenerMensajesNoLeidos(Long salaId) {
        return mensajeRepository.findBySalaIdAndLeido(salaId, false);
    }


    public Flux<Mensaje> streamMensajes() {
        return mensajeSink.asFlux();
    }

    public Flux<Mensaje> streamMensajesDeSala(Long salaId) {
        return mensajeSink.asFlux()
                .filter(mensaje -> mensaje.getSalaId().equals(salaId));
    }

    public Mono<Mensaje> marcarComoLeido(Long mensajeId, Long lectorId) {
        return mensajeRepository.findById(mensajeId)
                .flatMap(mensaje -> {
                    System.out.println("Mensaje encontrado: " + mensaje.getId());
                    mensaje.setLeido(true);
                    mensaje.setLeidoPor(lectorId);
                    return mensajeRepository.save(mensaje);
                })
                .switchIfEmpty(Mono.error(new RuntimeException("Mensaje no encontrado")));
    }
}
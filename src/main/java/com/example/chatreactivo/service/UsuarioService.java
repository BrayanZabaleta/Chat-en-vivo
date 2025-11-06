package com.example.chatreactivo.service;

import com.example.chatreactivo.entity.Usuario;
import com.example.chatreactivo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Mono<Usuario> crearUsuario(Usuario usuario) {
        usuario.setFechaCreacion(LocalDateTime.now());
        usuario.setActivo(true);
        return usuarioRepository.save(usuario);
    }

    public Mono<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Flux<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Flux<Usuario> obtenerActivos() {
        return usuarioRepository.findByActivo(true);
    }

    public Mono<Usuario> actualizarConexion(Long id) {
        return usuarioRepository.findById(id)
                .flatMap(usuario -> {
                    usuario.setUltimaConexion(LocalDateTime.now(java.time.ZoneId.systemDefault()));
                    return usuarioRepository.save(usuario);
                });
    }

    public Mono<Void> eliminar(Long id) {
        return usuarioRepository.deleteById(id);
    }
}
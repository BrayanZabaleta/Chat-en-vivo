package com.example.chatreactivo.repository;

import com.example.chatreactivo.entity.Usuario;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UsuarioRepository extends R2dbcRepository<Usuario, Long> {

    Mono<Usuario> findByEmail(String email);

    Flux<Usuario> findByActivo(Boolean activo);

    @Query("SELECT * FROM usuarios WHERE nombre ILIKE CONCAT('%', :nombre, '%')")
    Flux<Usuario> buscarPorNombre(String nombre);
}
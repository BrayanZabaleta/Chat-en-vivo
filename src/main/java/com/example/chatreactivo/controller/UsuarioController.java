package com.example.chatreactivo.controller;


import com.example.chatreactivo.entity.Usuario;
import com.example.chatreactivo.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Usuario> crear(@RequestBody Usuario usuario) {
        return usuarioService.crearUsuario(usuario);
    }

    @GetMapping("/{id}")
    public Mono<Usuario> obtenerPorId(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id);
    }

    @GetMapping
    public Flux<Usuario> obtenerTodos() {
        return usuarioService.obtenerTodos();
    }

    @GetMapping("/activos")
    public Flux<Usuario> obtenerActivos() {
        return usuarioService.obtenerActivos();
    }

    @PutMapping("/{id}/conexion")
    public Mono<Usuario> actualizarConexion(@PathVariable Long id) {
        return usuarioService.actualizarConexion(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> eliminar(@PathVariable Long id) {
        return usuarioService.eliminar(id);
    }
    @GetMapping("/")
    public Mono<Map<String, String>> healthCheck() {
        // Devuelve el estado de la API, respondiendo al request GET /
        return Mono.just(Map.of("status", "API OK"));
    }

}
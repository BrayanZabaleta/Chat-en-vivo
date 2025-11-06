package com.example.chatreactivo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("mensajes")
public class Mensaje {

    @Id
    private Long id;

    private Long salaId;
    private Long usuarioId;
    private String contenido;
    private String tipo; // texto, imagen, archivo
    private LocalDateTime fechaEnvio;

    private Boolean leido = false;
    private Long leidoPor;  // <-- NUEVO: quién lo leyó
}
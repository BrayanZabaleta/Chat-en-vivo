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
@Table("salas")
public class Sala {

    @Id
    private Long id;

    private String nombre;
    private String descripcion;
    private Long creadorId;
    private Boolean publica;
    private LocalDateTime fechaCreacion;
}
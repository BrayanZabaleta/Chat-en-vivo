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
@Table("usuarios")
public class Usuario {

    @Id
    private Long id;

    private String nombre;
    private String email;
    private String avatar;
    private Boolean activo;
    private LocalDateTime fechaCreacion;


    private LocalDateTime ultimaConexion;

    public void setUltimaConexion(LocalDateTime lastLogin) {
        this.ultimaConexion = lastLogin;
    }
}
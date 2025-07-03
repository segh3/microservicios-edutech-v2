package com.etch.inscripciones.inscripcion.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class InscripcionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInscripcion;
    @Column(name = "cod_inscripcion")
    private String codInscripcion;
    @Column(name = "id_usuario")
    private int idUsuario;
    @Column(name = "codigo_curso")
    private String codigoCurso;
    @Column(name = "fecha_inscripcion")
    private String fechaInscripcion;
    @Column(name = "estado")
    private boolean estado;

}

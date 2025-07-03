package com.etech.cursos.curso.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CursoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCurso;
    @Column(name = "codigoCurso")
    private String codigoCurso;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fechaCreacion")
    private String fechaCreacion;
    @Column(name = "idProfesor")
    private int idProfesor;
    @Column(name = "estado")
    private boolean estado;

}

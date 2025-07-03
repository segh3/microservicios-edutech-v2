package com.etech.cursos.curso.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Curso {
    private int idCurso;
    private String codigoCurso;
    private String nombreCurso;
    private String descripcion;
    private String fechaCreacion;
    private int idProfesor;
    private boolean estado;

}

package com.etch.inscripciones.inscripcion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inscripcion {
    private int idInscripcion;
    private String codInscripcion;
    private int idUsuario;
    private String codigoCurso;
    private String fechaInscripcion;
    private boolean estado;

}

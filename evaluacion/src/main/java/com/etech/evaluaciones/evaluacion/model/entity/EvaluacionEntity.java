package com.etech.evaluaciones.evaluacion.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class EvaluacionEntity {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEvaluacion;
    @Column(name = "fechaEvaluacion")
    private String fechaEvaluacion;
    @Column(name = "notaEvaluacion")
    private double notaEvaluacion;
    @Column(name = "cod")
    private String codigo;
}

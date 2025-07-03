package com.etch.inscripciones.inscripcion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etch.inscripciones.inscripcion.model.entity.InscripcionEntity;

public interface InscripcionRepository extends JpaRepository<InscripcionEntity, Integer>{
    InscripcionEntity findByCodInscripcion(String codInscripcion);
    Boolean existsByCodInscripcion(String codInscripcion);
    void deleteByCodInscripcion(String codInscripcion);
}

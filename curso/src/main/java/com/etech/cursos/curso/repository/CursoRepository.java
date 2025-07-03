package com.etech.cursos.curso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etech.cursos.curso.model.entity.CursoEntity;

public interface CursoRepository extends JpaRepository<CursoEntity, Integer> {
    CursoEntity findByCodigoCurso(String codigoCurso);
    Boolean existsByCodigoCurso(String codigoCurso);
    void deleteByCodigoCurso(String codigoCurso);
    List<CursoEntity> findByIdProfesor(int idProfesor);
    List<CursoEntity> findByEstado(boolean estado);
}

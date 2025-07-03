package com.etech.cursos.curso.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etech.cursos.curso.model.Curso;
import com.etech.cursos.curso.model.entity.CursoEntity;
import com.etech.cursos.curso.repository.CursoRepository;

import jakarta.transaction.Transactional;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;
    public String crearCurso(Curso curso){
        try {
            Boolean estado = cursoRepository.existsByCodigoCurso(curso.getCodigoCurso());
            if (!estado) {
                CursoEntity cursoNew = new CursoEntity();
                cursoNew.setIdCurso(curso.getIdCurso());
                cursoNew.setCodigoCurso(curso.getCodigoCurso());
                cursoNew.setNombre(curso.getNombreCurso());
                cursoNew.setDescripcion(curso.getDescripcion());
                cursoNew.setFechaCreacion(curso.getFechaCreacion());
                cursoNew.setIdProfesor(curso.getIdProfesor());
                cursoNew.setEstado(curso.isEstado());
                cursoRepository.save(cursoNew);
                return "Curso creado correctamente";
            }        
            return "El curso ya existe";
        }catch(Exception e) {
            return "Error al crear el curso ";
        }
    }


    public Curso obtenerCurso(String codigoCurso){
        try {
            CursoEntity mostrarCurso= cursoRepository.findByCodigoCurso(codigoCurso);
            if (mostrarCurso != null) {
                Curso curso = new Curso(
                    mostrarCurso.getIdCurso(),
                    mostrarCurso.getCodigoCurso(),
                    mostrarCurso.getNombre(),
                    mostrarCurso.getDescripcion(),
                    mostrarCurso.getFechaCreacion(),
                    mostrarCurso.getIdProfesor(),
                    mostrarCurso.isEstado()
                );
                return curso;
            }
            return null;
        }catch(Exception e) {
            return null;
        }
    }

    public String actualizarCurso(Curso curso){
        try {
            CursoEntity mostrarCurso= cursoRepository.findByCodigoCurso(curso.getCodigoCurso());
            if (mostrarCurso != null) {
                mostrarCurso.setNombre(curso.getNombreCurso());
                mostrarCurso.setDescripcion(curso.getDescripcion());
                mostrarCurso.setFechaCreacion(curso.getFechaCreacion());
                mostrarCurso.setIdProfesor(curso.getIdProfesor());
                mostrarCurso.setEstado(curso.isEstado());
                cursoRepository.save(mostrarCurso);
                return "Curso actualizado correctamente";
            }
            return "El curso no existe";
        }catch(Exception e) {
            return "Error al actualizar el curso";
        }
    }

    @Transactional
    public String eliminarCurso(String codigoCurso){
        try {
            CursoEntity mostrarCurso= cursoRepository.findByCodigoCurso(codigoCurso);
            if (mostrarCurso != null) {
                cursoRepository.deleteByCodigoCurso(codigoCurso);
                return "Curso eliminado correctamente";
            }
            return "El curso no existe";
        }catch(Exception e) {
            return "Error al eliminar el curso";
        }
    }

    public List <Curso> listarCursosProfesor(int idProfesor){
        try {
            List<CursoEntity> mostrarCursos= cursoRepository.findByIdProfesor(idProfesor);
            List<Curso> cursos = new ArrayList<>();
            for (CursoEntity cursoEntity : mostrarCursos) {
                Curso curso = new Curso(
                    cursoEntity.getIdCurso(),
                    cursoEntity.getCodigoCurso(),
                    cursoEntity.getNombre(),
                    cursoEntity.getDescripcion(),
                    cursoEntity.getFechaCreacion(),
                    cursoEntity.getIdProfesor(),
                    cursoEntity.isEstado()
                );
                cursos.add(curso);
            }
            return cursos;
        }catch(Exception e) {
            return null;
        }
    } 

    public List <Curso> listarCursosEstado(boolean estado){
        try {
            List<CursoEntity> mostrarCursos= cursoRepository.findByEstado(estado);
            List<Curso> cursos = new ArrayList<>();
            for (CursoEntity cursoEntity : mostrarCursos) {
                Curso curso = new Curso(
                    cursoEntity.getIdCurso(),
                    cursoEntity.getCodigoCurso(),
                    cursoEntity.getNombre(),
                    cursoEntity.getDescripcion(),
                    cursoEntity.getFechaCreacion(),
                    cursoEntity.getIdProfesor(),
                    cursoEntity.isEstado()
                );
                cursos.add(curso);
            }
            return cursos;
        }catch(Exception e) {
            return null;
        }
    }
    

}

package com.etech.cursos.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.etech.cursos.curso.model.Curso;
import com.etech.cursos.curso.service.CursoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @Operation(summary = "Crear un nuevo curso")
    @PostMapping("/crearCurso")
    public ResponseEntity<String> crearCurso(@RequestBody Curso curso) {
        return ResponseEntity.ok(cursoService.crearCurso(curso));
    }

    @Operation(summary = "Obtener un curso por su código")
    @GetMapping("/obtenerCurso/{codigoCurso}")
    public ResponseEntity<Curso> obtenerCurso(@PathVariable String codigoCurso) {
        Curso curso = cursoService.obtenerCurso(codigoCurso);
        if (curso != null) {
            return ResponseEntity.ok(curso);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Listar todos los cursos")
    @GetMapping("/listarCursosPorProfesor/{idProfesor}")
    public ResponseEntity<List<Curso>> listarCursosProfesor(@PathVariable int idProfesor) {
        return ResponseEntity.ok(cursoService.listarCursosProfesor(idProfesor));
    }

    @Operation(summary = "Listar todos los cursos")
    @PutMapping("/actualizarCurso")
    public ResponseEntity<String> actualizarCurso(@RequestBody Curso curso) {
        return ResponseEntity.ok(cursoService.actualizarCurso(curso));
    }
    
    @Operation(summary = "Eliminar un curso por su código")
    @DeleteMapping("/eliminarCurso/{codigoCurso}")
    public ResponseEntity<String> eliminarCurso(@PathVariable String codigoCurso) {
        return ResponseEntity.ok(cursoService.eliminarCurso(codigoCurso));
    }
}

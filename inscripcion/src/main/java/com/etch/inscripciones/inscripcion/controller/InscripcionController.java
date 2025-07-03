package com.etch.inscripciones.inscripcion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.etch.inscripciones.inscripcion.model.Inscripcion;
import com.etch.inscripciones.inscripcion.service.InscripcionService;

@RestController
public class InscripcionController {
    @Autowired
    private InscripcionService inscripcionService;

    @PostMapping("/crearInscripcion")
    public ResponseEntity<String> crearInscripcion(@RequestBody Inscripcion inscripcion) {
        return ResponseEntity.ok(inscripcionService.crearInscripcion(inscripcion));
    }

    @GetMapping("/obtenerInscripcion{codInscripcion}")
    public ResponseEntity<Inscripcion> obtenerInscripcion(@PathVariable String codInscripcion) {
        Inscripcion inscripcion = inscripcionService.obtenerInscripcion(codInscripcion);
        if(inscripcion != null){
            return ResponseEntity.ok(inscripcionService.obtenerInscripcion(codInscripcion));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/actualizarInscripcion")
    public ResponseEntity<String> actualizarInscripcion(@RequestBody Inscripcion inscripcion) {
        return ResponseEntity.ok(inscripcionService.actualizarInscripcion(inscripcion));
    }

    @DeleteMapping("/eliminarInscripcion/{codInscripcion}")
    public ResponseEntity<String> eliminarInscripcion(@PathVariable String codInscripcion) {
        return ResponseEntity.ok(inscripcionService.eliminarInscripcion(codInscripcion));
    }

}

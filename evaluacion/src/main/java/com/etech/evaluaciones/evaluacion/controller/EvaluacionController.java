package com.etech.evaluaciones.evaluacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.etech.evaluaciones.evaluacion.model.Evaluacion;
import com.etech.evaluaciones.evaluacion.service.EvaluacionService;


@RestController
public class EvaluacionController {
    @Autowired 
    private EvaluacionService evaluacionService;
    
    @PostMapping("/crearEvaluacion")
    public ResponseEntity<String> obtenerEvaluacion(@RequestBody Evaluacion evaluacion){
        return ResponseEntity.ok(evaluacionService.crearEvaluacion(evaluacion));
    }

    @GetMapping("/obtenerEvaluacion/{codigo}")
    public ResponseEntity<Evaluacion> obtenerEvaluacion(@PathVariable String codigo){
        Evaluacion evaluacion = evaluacionService.obtenerEvaluacion(codigo);
        if (evaluacion != null) {
            return ResponseEntity.ok(evaluacion);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/actualizarEvaluacion")
    public ResponseEntity<String> actualizarEvaluacion(@RequestBody Evaluacion evaluacion){
        return ResponseEntity.ok(evaluacionService.actualizarEvaluacion(evaluacion));
    }

    @DeleteMapping("/eliminarEvaluacion/{codigo}")
    public ResponseEntity<String> eliminarEvaluacion(@PathVariable String codigo){
        return ResponseEntity.ok(evaluacionService.eliminarEvaluacion(codigo));
    }

}

package com.etech.evaluaciones.evaluacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etech.evaluaciones.evaluacion.model.Evaluacion;
import com.etech.evaluaciones.evaluacion.model.entity.EvaluacionEntity;
import com.etech.evaluaciones.evaluacion.repository.EvaluacionRepository;

import jakarta.transaction.Transactional;

@Service
public class EvaluacionService {
     @Autowired 
    private EvaluacionRepository evaluacionRepository; 
    public String crearEvaluacion(Evaluacion evaluacion){
        try{
            Boolean estado = evaluacionRepository.existsByCodigo(evaluacion.getCodigo()); 
            if (!estado) {
                EvaluacionEntity evaluacionNuevo = new EvaluacionEntity(); 
                evaluacionNuevo.setIdEvaluacion(evaluacion.getIdEvaluacion()); 
                evaluacionNuevo.setFechaEvaluacion(evaluacion.getFechaEvaluacion()); 
                evaluacionNuevo.setNotaEvaluacion(evaluacion.getNotaEvaluacion());
                evaluacionNuevo.setCodigo(evaluacion.getCodigo()); 
                evaluacionRepository.save(evaluacionNuevo); 
                return "Evaluacion creada correctamente"; 
            }
            return "El codigo ya existe"; //Retorna un mensaje de error si el correo ya existe.
        }catch(Exception e){
            return "Error al crear evaluacion";
        }
        
    }

    public Evaluacion obtenerEvaluacion(String codigo){
        try{
            EvaluacionEntity evaluacion = evaluacionRepository.findByCodigo(codigo);
            if (evaluacion != null){
                Evaluacion eva = new Evaluacion(
                    evaluacion.getIdEvaluacion(),
                    evaluacion.getFechaEvaluacion(),
                    evaluacion.getNotaEvaluacion(),
                    evaluacion.getCodigo()
                );
                return eva;
            }return null;
        }catch(Exception e){
            return null;
        }
    }

    public String actualizarEvaluacion(Evaluacion eva){
        try{
            EvaluacionEntity evaluacion = evaluacionRepository.findByCodigo(eva.getCodigo());
            if (evaluacion != null){
                evaluacion.setIdEvaluacion(eva.getIdEvaluacion());
                evaluacion.setFechaEvaluacion(eva.getFechaEvaluacion());
                evaluacion.setNotaEvaluacion(eva.getNotaEvaluacion());
                evaluacionRepository.save(evaluacion);
                return "Evaluacion actualizada correctamente";
            }return "El codigo no existe";
        }catch(Exception e){
            return "Error al actualizar la evaluacion";
        }
    }

    @Transactional
    public String eliminarEvaluacion(String codigo){
        try{
            EvaluacionEntity evaluacion = evaluacionRepository.findByCodigo(codigo);
            if (evaluacion != null){
                evaluacionRepository.deleteByCodigo(codigo);
                return "La evaluacion eliminada correctamente";
            }return "La evaluacion no existe";
        }catch(Exception e){
            return "Error al eliminar la evaluacion";
        }
    }

}

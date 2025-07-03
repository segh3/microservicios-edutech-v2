package com.etch.inscripciones.inscripcion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etch.inscripciones.inscripcion.model.Inscripcion;
import com.etch.inscripciones.inscripcion.model.entity.InscripcionEntity;
import com.etch.inscripciones.inscripcion.repository.InscripcionRepository;

import jakarta.transaction.Transactional;

@Service
public class InscripcionService {
        @Autowired
    private InscripcionRepository inscripcionRepository;
    public String crearInscripcion(Inscripcion inscripcion){
        try{
            Boolean estado = inscripcionRepository.existsByCodInscripcion(inscripcion.getCodInscripcion());
            if(!estado){
                InscripcionEntity inscripcionNueva = new InscripcionEntity();
                inscripcionNueva.setCodInscripcion(inscripcion.getCodInscripcion());
                inscripcionNueva.setIdUsuario(inscripcion.getIdUsuario());
                inscripcionNueva.setCodigoCurso(inscripcion.getCodigoCurso());
                inscripcionNueva.setFechaInscripcion(inscripcion.getFechaInscripcion());
                inscripcionNueva.setEstado(inscripcion.isEstado());
                inscripcionRepository.save(inscripcionNueva);
                return "Inscripción creada correctamente";
            }return "La inscripción ya existe";
            }catch(Exception e){
                return "Error al crear la inscripción: " + e.getMessage();
            }
    }

    public Inscripcion obtenerInscripcion(String codInscripcion){
        try{
            InscripcionEntity inscripcionEntity = inscripcionRepository.findByCodInscripcion(codInscripcion);
            if(inscripcionEntity != null){
                Inscripcion inscripcion = new Inscripcion();
                inscripcion.setIdInscripcion(inscripcionEntity.getIdInscripcion());
                inscripcion.setCodInscripcion(inscripcionEntity.getCodInscripcion());
                inscripcion.setIdUsuario(inscripcionEntity.getIdUsuario());
                inscripcion.setCodigoCurso(inscripcionEntity.getCodigoCurso());
                inscripcion.setFechaInscripcion(inscripcionEntity.getFechaInscripcion());
                inscripcion.setEstado(inscripcionEntity.isEstado());
                return inscripcion;
            }return null;
        }catch(Exception e){
            return null;
        }
    }

    public String actualizarInscripcion(Inscripcion inscripcion){
        try{
            InscripcionEntity inscripcionEntity = inscripcionRepository.findByCodInscripcion(inscripcion.getCodInscripcion());
            if(inscripcionEntity != null){
                inscripcionEntity.setIdUsuario(inscripcion.getIdUsuario());
                inscripcionEntity.setCodigoCurso(inscripcion.getCodigoCurso());
                inscripcionEntity.setFechaInscripcion(inscripcion.getFechaInscripcion());
                inscripcionEntity.setEstado(inscripcion.isEstado());
                inscripcionRepository.save(inscripcionEntity);
                return "Inscripción actualizada correctamente";
            }return "La inscripción no existe";
        }catch(Exception e){
            return "Error al actualizar la inscripción: " + e.getMessage();
        }
    }

    @Transactional
    public String eliminarInscripcion(String codInscripcion){
        try{
            Boolean estado = inscripcionRepository.existsByCodInscripcion(codInscripcion);
            if(estado){
                inscripcionRepository.deleteByCodInscripcion(codInscripcion);
                return "Inscripción eliminada correctamente";
            }return "La inscripción no existe";
        }catch(Exception e){
            return "Error al eliminar la inscripción: " + e.getMessage();
        }
    }

}

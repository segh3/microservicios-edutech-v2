package com.etech.evaluaciones.evaluacion.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.etech.evaluaciones.evaluacion.model.Evaluacion;
import com.etech.evaluaciones.evaluacion.model.entity.EvaluacionEntity;
import com.etech.evaluaciones.evaluacion.repository.EvaluacionRepository;

@ExtendWith(MockitoExtension.class)
public class EvaluacionServiceTest {

    @Mock
    private EvaluacionRepository evaluacionRepository;

    @InjectMocks
    private EvaluacionService evaluacionService;

    private Evaluacion evaluacionDePrueba;
    private EvaluacionEntity evaluacionEntityDePrueba;

    @BeforeEach
    void setUp() {
        evaluacionDePrueba = new Evaluacion();
        evaluacionDePrueba.setIdEvaluacion(1); 
        evaluacionDePrueba.setFechaEvaluacion("2023-10-26"); 
        evaluacionDePrueba.setNotaEvaluacion(7.5);
        evaluacionDePrueba.setCodigo("EVAL001");

        evaluacionEntityDePrueba = new EvaluacionEntity();
        evaluacionEntityDePrueba.setIdEvaluacion(1);
        evaluacionEntityDePrueba.setFechaEvaluacion("2023-10-26");
        evaluacionEntityDePrueba.setNotaEvaluacion(7.5);
        evaluacionEntityDePrueba.setCodigo("EVAL001");
    }

    
    @Test
    void testCrearEvaluacion_Exito() {
        when(evaluacionRepository.existsByCodigo(evaluacionDePrueba.getCodigo())).thenReturn(false);

        when(evaluacionRepository.save(any(EvaluacionEntity.class))).thenReturn(evaluacionEntityDePrueba);

        String resultado = evaluacionService.crearEvaluacion(evaluacionDePrueba);

        assertEquals("Evaluacion creada correctamente", resultado);

        verify(evaluacionRepository, times(1)).save(any(EvaluacionEntity.class));
    }

    @Test
    void testCrearEvaluacion_CodigoYaExiste() {

        when(evaluacionRepository.existsByCodigo(evaluacionDePrueba.getCodigo())).thenReturn(true);

        String resultado = evaluacionService.crearEvaluacion(evaluacionDePrueba);

        assertEquals("El codigo ya existe", resultado);

        verify(evaluacionRepository, never()).save(any(EvaluacionEntity.class));
    }

    @Test
    void testCrearEvaluacion_ErrorAlGuardar() {

        when(evaluacionRepository.existsByCodigo(evaluacionDePrueba.getCodigo())).thenReturn(false);

        when(evaluacionRepository.save(any(EvaluacionEntity.class))).thenThrow(new RuntimeException("Error de base de datos simulado"));

        String resultado = evaluacionService.crearEvaluacion(evaluacionDePrueba);

        assertEquals("Error al crear evaluacion", resultado);

        verify(evaluacionRepository, times(1)).save(any(EvaluacionEntity.class));
    }


    @Test
    void testObtenerEvaluacion_Exito() {

        when(evaluacionRepository.findByCodigo(evaluacionDePrueba.getCodigo())).thenReturn(evaluacionEntityDePrueba);


        Evaluacion resultado = evaluacionService.obtenerEvaluacion(evaluacionDePrueba.getCodigo());


        assertNotNull(resultado);
        assertEquals(evaluacionDePrueba.getIdEvaluacion(), resultado.getIdEvaluacion());
        assertEquals(evaluacionDePrueba.getFechaEvaluacion(), resultado.getFechaEvaluacion());
        assertEquals(evaluacionDePrueba.getNotaEvaluacion(), resultado.getNotaEvaluacion());
        assertEquals(evaluacionDePrueba.getCodigo(), resultado.getCodigo());

        verify(evaluacionRepository, times(1)).findByCodigo(evaluacionDePrueba.getCodigo());
    }

    @Test
    void testObtenerEvaluacion_NoExiste() {
        when(evaluacionRepository.findByCodigo("COD_INEXISTENTE")).thenReturn(null);

        Evaluacion resultado = evaluacionService.obtenerEvaluacion("COD_INEXISTENTE");

        assertNull(resultado);

        verify(evaluacionRepository, times(1)).findByCodigo("COD_INEXISTENTE");
    }

    @Test
    void testObtenerEvaluacion_ErrorAlObtener() {

        when(evaluacionRepository.findByCodigo(evaluacionDePrueba.getCodigo())).thenThrow(new RuntimeException("Error de base de datos simulado"));

        Evaluacion resultado = evaluacionService.obtenerEvaluacion(evaluacionDePrueba.getCodigo());

        assertNull(resultado);

        verify(evaluacionRepository, times(1)).findByCodigo(evaluacionDePrueba.getCodigo());
    }

    @Test
    void testActualizarEvaluacion_Exito() {

        Evaluacion evaluacionActualizada = new Evaluacion(1, "2024-01-15", 8.0, "EVAL001");

        when(evaluacionRepository.findByCodigo(evaluacionDePrueba.getCodigo())).thenReturn(evaluacionEntityDePrueba);

        when(evaluacionRepository.save(any(EvaluacionEntity.class))).thenReturn(evaluacionEntityDePrueba);

        String resultado = evaluacionService.actualizarEvaluacion(evaluacionActualizada);

        assertEquals("Evaluacion actualizada correctamente", resultado);

        verify(evaluacionRepository, times(1)).findByCodigo(evaluacionDePrueba.getCodigo());
       
        verify(evaluacionRepository, times(1)).save(any(EvaluacionEntity.class));
    }

    @Test
    void testActualizarEvaluacion_NoExiste() {
       
        when(evaluacionRepository.findByCodigo("COD_INEXISTENTE")).thenReturn(null);

        Evaluacion evaluacionInexistente = new Evaluacion(99, "2024-01-15", 8.0, "COD_INEXISTENTE");

        String resultado = evaluacionService.actualizarEvaluacion(evaluacionInexistente);

        assertEquals("El codigo no existe", resultado);
        
        verify(evaluacionRepository, never()).save(any(EvaluacionEntity.class));
    
        verify(evaluacionRepository, times(1)).findByCodigo("COD_INEXISTENTE");
    }

    @Test
    void testActualizarEvaluacion_ErrorAlActualizar() {
        
        when(evaluacionRepository.findByCodigo(evaluacionDePrueba.getCodigo())).thenReturn(evaluacionEntityDePrueba);
       
        when(evaluacionRepository.save(any(EvaluacionEntity.class))).thenThrow(new RuntimeException("Error de base de datos simulado"));

        String resultado = evaluacionService.actualizarEvaluacion(evaluacionDePrueba);

        assertEquals("Error al actualizar la evaluacion", resultado);
        
        verify(evaluacionRepository, times(1)).findByCodigo(evaluacionDePrueba.getCodigo());
        
        verify(evaluacionRepository, times(1)).save(any(EvaluacionEntity.class));
    }

    @Test
    void testEliminarEvaluacion_Exito() {
        
        when(evaluacionRepository.findByCodigo(evaluacionDePrueba.getCodigo())).thenReturn(evaluacionEntityDePrueba);
        
        doNothing().when(evaluacionRepository).deleteByCodigo(evaluacionDePrueba.getCodigo());

        String resultado = evaluacionService.eliminarEvaluacion(evaluacionDePrueba.getCodigo());

        assertEquals("La evaluacion eliminada correctamente", resultado);
        
        verify(evaluacionRepository, times(1)).findByCodigo(evaluacionDePrueba.getCodigo());
        
        verify(evaluacionRepository, times(1)).deleteByCodigo(evaluacionDePrueba.getCodigo());
    }

    @Test
    void testEliminarEvaluacion_NoExiste() {
    
        when(evaluacionRepository.findByCodigo("COD_INEXISTENTE")).thenReturn(null);

        String resultado = evaluacionService.eliminarEvaluacion("COD_INEXISTENTE");

        assertEquals("La evaluacion no existe", resultado);
        
        verify(evaluacionRepository, never()).deleteByCodigo(any(String.class));
        
        verify(evaluacionRepository, times(1)).findByCodigo("COD_INEXISTENTE");
    }

    @Test
    void testEliminarEvaluacion_ErrorAlEliminar() {
        
        when(evaluacionRepository.findByCodigo(evaluacionDePrueba.getCodigo())).thenReturn(evaluacionEntityDePrueba);
        
        doThrow(new RuntimeException("Error de base de datos simulado")).when(evaluacionRepository).deleteByCodigo(evaluacionDePrueba.getCodigo());

        String resultado = evaluacionService.eliminarEvaluacion(evaluacionDePrueba.getCodigo());

        assertEquals("Error al eliminar la evaluacion", resultado);
        
        verify(evaluacionRepository, times(1)).findByCodigo(evaluacionDePrueba.getCodigo());
        
        verify(evaluacionRepository, times(1)).deleteByCodigo(evaluacionDePrueba.getCodigo());
    }
}


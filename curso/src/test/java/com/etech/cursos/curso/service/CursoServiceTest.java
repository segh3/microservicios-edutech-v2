package com.etech.cursos.curso.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.etech.cursos.curso.model.Curso;
import com.etech.cursos.curso.model.entity.CursoEntity;
import com.etech.cursos.curso.repository.CursoRepository;


/*
 * Pruebas unitarias para el servicio de cursos.
 * Se utilizan Mockito y JUnit 5 para simular el repositorio y verificar el comportamiento del servicio.
 */
@ExtendWith(MockitoExtension.class)
public class CursoServiceTest {

@Mock
private CursoRepository cursoRepository;

@InjectMocks
private CursoService cursoService;

private Curso curso;
private CursoEntity cursoEntity;

@BeforeEach
void setUp(){
    // Inicializar el DTO de prueba (Curso)
    curso = new Curso();
    curso.setCodigoCurso("C001");
    curso.setNombreCurso("Curso de Prueba");
    curso.setDescripcion("Descripción del curso de prueba");
    curso.setFechaCreacion("2023-10-01");
    curso.setIdProfesor(1);
    curso.setEstado(true);
    // Inicializar la entidad de prueba (CursoEntity)
    cursoEntity = new CursoEntity();
    cursoEntity.setCodigoCurso("C001");
    cursoEntity.setNombre("Curso de Prueba");
    cursoEntity.setDescripcion("Descripción del curso de prueba");
    cursoEntity.setFechaCreacion("2023-10-01");
    cursoEntity.setIdProfesor(1);
    cursoEntity.setEstado(true);
    }

    // 1. Test: Creación exitosa de un curso
    @Test
    void crearCurso_Success() {
        // Dado que el código del curso no existe
        when(cursoRepository.existsByCodigoCurso(curso.getCodigoCurso())).thenReturn(false);
        // Y cuando se llama al método save, devolver la entidad
        when(cursoRepository.save(any(CursoEntity.class))).thenReturn(cursoEntity);

        // Cuando se crea el curso
        String result = cursoService.crearCurso(curso);

        // Entonces, afirmar que se devuelve el mensaje de éxito
        assertEquals("Curso creado correctamente", result);
        // Y verificar que los métodos existsByCodigoCurso y save fueron llamados una vez
        verify(cursoRepository, times(1)).existsByCodigoCurso(curso.getCodigoCurso());
        verify(cursoRepository, times(1)).save(any(CursoEntity.class));
    }

    // 2. Test: Creación de un curso cuando ya existe
    @Test
    void crearCurso_AlreadyExists() {
        // Dado que el código del curso ya existe
        when(cursoRepository.existsByCodigoCurso(curso.getCodigoCurso())).thenReturn(true);

        // Cuando se crea el curso
        String result = cursoService.crearCurso(curso);

        // Entonces, afirmar que se devuelve el mensaje de "ya existe"
        assertEquals("El curso ya existe", result);
        // Y verificar que existsByCodigoCurso fue llamado, pero save no lo fue
        verify(cursoRepository, times(1)).existsByCodigoCurso(curso.getCodigoCurso());
        verify(cursoRepository, never()).save(any(CursoEntity.class));
    }

    // 3. Test: Recuperar un curso exitosamente por código
    @Test
    void obtenerCurso_Found() {
        // Dado que la búsqueda por código devuelve una entidad
        when(cursoRepository.findByCodigoCurso(curso.getCodigoCurso())).thenReturn(cursoEntity);

        // Cuando se recupera el curso
        Curso result = cursoService.obtenerCurso(curso.getCodigoCurso());

        // Entonces, afirmar que el curso se encuentra y su código coincide
        assertNotNull(result);
        assertEquals(curso.getCodigoCurso(), result.getCodigoCurso());
        // Y verificar que findByCodigoCurso fue llamado
        verify(cursoRepository, times(1)).findByCodigoCurso(curso.getCodigoCurso());
    }

    // 4. Test: Actualizar un curso existente exitosamente
    @Test
    void actualizarCurso_Success() {
        // Dado que la búsqueda por código devuelve una entidad
        when(cursoRepository.findByCodigoCurso(curso.getCodigoCurso())).thenReturn(cursoEntity);
        // Y guardar la entidad actualizada la devuelve
        when(cursoRepository.save(any(CursoEntity.class))).thenReturn(cursoEntity);

        // Cuando se actualiza el curso
        String result = cursoService.actualizarCurso(curso);

        // Entonces, afirmar el mensaje de éxito
        assertEquals("Curso actualizado correctamente", result);
        // Y verificar que findByCodigoCurso y save fueron llamados
        verify(cursoRepository, times(1)).findByCodigoCurso(curso.getCodigoCurso());
        verify(cursoRepository, times(1)).save(cursoEntity);
    }

    // 5. Test: Eliminar un curso exitosamente por código
    @Test
    void eliminarCurso_Success() {
        // Dado que la búsqueda por código devuelve una entidad
        when(cursoRepository.findByCodigoCurso(curso.getCodigoCurso())).thenReturn(cursoEntity);
        // Y el método delete no hace nada (llamada exitosa a un método void)
        doNothing().when(cursoRepository).deleteByCodigoCurso(curso.getCodigoCurso());

        // Cuando se elimina el curso
        String result = cursoService.eliminarCurso(curso.getCodigoCurso());

        // Entonces, afirmar el mensaje de éxito
        assertEquals("Curso eliminado correctamente", result);
        // Y verificar que findByCodigoCurso y deleteByCodigoCurso fueron llamados
        verify(cursoRepository, times(1)).findByCodigoCurso(curso.getCodigoCurso());
        verify(cursoRepository, times(1)).deleteByCodigoCurso(curso.getCodigoCurso());
    }

    // 6. Test: Listar cursos por ID de profesor
    @Test
    void listarCursosProfesor_Found() {
        // Dada una lista de entidades para un profesor específico
        List<CursoEntity> entities = Arrays.asList(cursoEntity, new CursoEntity() {{ // Clase interna anónima para configuración rápida
            setIdCurso(2); setCodigoCurso("C002"); setNombre("Otro Curso");
            setDescripcion("Descripción 2"); setFechaCreacion("2023-10-02");
            setIdProfesor(1); setEstado(true);
        }});
        when(cursoRepository.findByIdProfesor(1)).thenReturn(entities);

        // Cuando se listan los cursos por ID de profesor
        List<Curso> result = cursoService.listarCursosProfesor(1);

        
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("C001", result.get(0).getCodigoCurso());
        assertEquals("C002", result.get(1).getCodigoCurso());
        // Verificar que el repositorio fue llamado con el ID del profesor
        verify(cursoRepository, times(1)).findByIdProfesor(1);
    }
}
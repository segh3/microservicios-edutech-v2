package com.edutech.usuarios.users.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.edutech.usuarios.users.model.Usuario;
import com.edutech.usuarios.users.model.entity.UsuarioEntity;
import com.edutech.usuarios.users.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks    
    private UsuarioService usuarioService;

    private Usuario usuarioDePrueba;
    private UsuarioEntity usuarioEntityDePrueba;

    @BeforeEach
    void setUp(){
        // Inicializa el objeto DTO (Usuario)
        usuarioDePrueba = new Usuario();
        usuarioDePrueba.setCorreo("seba.com");
        usuarioDePrueba.setNombre("Sebastian");
        usuarioDePrueba.setApPat("Gutierrez");
        usuarioDePrueba.setApMat("Hernandez");
        usuarioDePrueba.setContrasena("seba123");
        usuarioDePrueba.setRol("Estudiante");
        usuarioDePrueba.setEstado("Activo");

        // Inicializa el objeto Entity (UsuarioEntity) para simular la base de datos
        usuarioEntityDePrueba = new UsuarioEntity();
        usuarioEntityDePrueba.setIdUsuario(1);
        usuarioEntityDePrueba.setCorreo("seba.com");
        usuarioEntityDePrueba.setNombre("Sebastian");
        usuarioEntityDePrueba.setApPat("Gutierrez");
        usuarioEntityDePrueba.setApMat("Hernandez");
        usuarioEntityDePrueba.setContrasena("seba123");
        usuarioEntityDePrueba.setRol("Estudiante");
        usuarioEntityDePrueba.setEstado("Activo");
    }

    //Prueba la creación de un usuario exitoso.
   @Test
    void testCrearUsuarioExitoso() {
        // Simula que el correo no existe y que el guardado es exitoso.
        when(usuarioRepository.existsByCorreo(usuarioDePrueba.getCorreo())).thenReturn(false);
        when(usuarioRepository.save(any(UsuarioEntity.class))).thenReturn(usuarioEntityDePrueba);

        // Llama al método a probar.
        String resultado = usuarioService.crearUsuario(usuarioDePrueba);

        // Verifica que el servicio retorna el mensaje de éxito.
        assertEquals("Usuario creado correctamente", resultado);
    }

    //Prueba la creación cuando el correo ya existe.
    @Test
    void testCrearUsuario_CorreoYaExiste() {
        // Simula que el correo ya existe.
        when(usuarioRepository.existsByCorreo(usuarioDePrueba.getCorreo())).thenReturn(true);

        // Llama al método a probar.
        String resultado = usuarioService.crearUsuario(usuarioDePrueba);

        // Verifica el mensaje de conflicto y que el guardado no se ejecutó.
        assertEquals("El correo ya existe", resultado);
        verify(usuarioRepository, never()).save(any(UsuarioEntity.class));
    }

    
    //Prueba el caso de éxito al obtener un usuario.
    @Test
    void testObtenerUsuario_Exitoso() {
        // Simula que el repositorio encuentra y devuelve la entidad.
        when(usuarioRepository.findByCorreo(usuarioDePrueba.getCorreo())).thenReturn(usuarioEntityDePrueba);

        // Llama al método a probar.
        Usuario resultado = usuarioService.obtenerUsuario(usuarioDePrueba.getCorreo());

        // Verifica que se devolvió el objeto correcto.
        assertNotNull(resultado);
        assertEquals(usuarioDePrueba.getCorreo(), resultado.getCorreo());
    }

    //Prueba el caso cuando un usuario no existe.
    @Test
    void testObtenerUsuario_NoExiste() {
        // Simula que el repositorio no encuentra el usuario y devuelve null.
        when(usuarioRepository.findByCorreo("correo-inexistente@test.com")).thenReturn(null);

        // Llama al método a probar.
        Usuario resultado = usuarioService.obtenerUsuario("correo-inexistente@test.com");

        // Verifica que el resultado es nulo.
        assertEquals(null, resultado);
    }
}

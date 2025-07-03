package com.edutech.usuarios.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.usuarios.users.model.Usuario;
import com.edutech.usuarios.users.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class UsuarioController {
    @Autowired 
    private UsuarioService usuarioService;

    @Operation(summary = "Este endpoint permite crear un nuevo usuario y validar si el usuario ya existe")
    @PostMapping("/crearUsuario")
    public ResponseEntity<String> crearUsuario(@RequestBody Usuario usuario){
        Usuario usuarioExistente = usuarioService.obtenerUsuario(usuario.getCorreo());
        if (usuarioExistente != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya existe"); //Devuelve un 409 si el usuario ya existe
        }
        String resultado = usuarioService.crearUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado); //Devuelve un 201 si el usuario se crea correctamente
    }

    @Operation(summary = "Este endpoint permite obtener un usuario por su correo")
    @GetMapping("/obtenerUsuario/{correo}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable String correo){
        Usuario usuario = usuarioService.obtenerUsuario(correo);
        if (usuario != null) {
            return ResponseEntity.ok(usuario); //Devuelve un 200 si el usuario si existe
        }
        return ResponseEntity.notFound().build(); //Devuelve un 404 si el usuario no existe
    }

    @Operation(summary = "Este endpoint permite actualizar un usuario existente")
    @PutMapping("/actualizarUsuario")
    public ResponseEntity<String> actualizarUsuario(@RequestBody Usuario usuario){
        Usuario usuarioExistente = usuarioService.obtenerUsuario(usuario.getCorreo());
        if (usuarioExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no existe"); //Devuelve un 404 si el usuario no existe
        }
        String resultado = usuarioService.actualizarUsuario(usuarioExistente);
        return ResponseEntity.ok(resultado); //Devuelve un 200 si el usuario se actualiza correctamente
    }

    @Operation(summary = "Este endpoint permite eliminar un usuario por su correo")
    @DeleteMapping("/eliminarUsuario/{correo}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable String correo){
        Usuario usuarioExistente = usuarioService.obtenerUsuario(correo);
        if (usuarioExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no existe"); //Devuelve un 404 si el usuario no existe
        }
        usuarioService.eliminarUsuario(correo); //Elimina el usuario
        return ResponseEntity.noContent().build(); //Devuelve un 204 si el usuario se elimina correctamente
    }
}

package com.edutech.usuarios.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.usuarios.users.model.Usuario;
import com.edutech.usuarios.users.model.entity.UsuarioEntity;
import com.edutech.usuarios.users.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
    @Autowired 
    private UsuarioRepository usuarioRepository; //Inyeccion de dependencias para el repositorio de usuarios.
    public String crearUsuario(Usuario user){
        try{
            Boolean estado = usuarioRepository.existsByCorreo(user.getCorreo()); //Verifica si el correo ya existe en la base de datos.
            if (!estado) {
                UsuarioEntity usuarioNuevo = new UsuarioEntity(); //Crea un nuevo objeto de tipo UsuarioEntity.
                usuarioNuevo.setIdUsuario(user.getIdUsuario()); //Asigna el id del usuario.
                usuarioNuevo.setNombre(user.getNombre()); //Asigna el nombre del usuario.
                usuarioNuevo.setApPat(user.getApPat()); //Asigna el apellido paterno del usuario.
                usuarioNuevo.setApMat(user.getApMat()); //Asigna el apellido materno del usuario.
                usuarioNuevo.setCorreo(user.getCorreo()); //Asigna el correo del usuario.
                usuarioNuevo.setContrasena(user.getContrasena()); //Asigna la contraseña del usuario.
                usuarioNuevo.setRol(user.getRol()); //Asigna el rol del usuario.
                usuarioNuevo.setEstado(user.getEstado()); //Asigna el estado del usuario.
                usuarioRepository.save(usuarioNuevo); //Guarda el nuevo usuario en la base de datos.
                return "Usuario creado correctamente"; //Retorna un mensaje de exito.
            }
            return "El correo ya existe"; //Retorna un mensaje de error si el correo ya existe.
        }catch(Exception e){
            return "Error al crear el usuario";
        }
        
    }

    public Usuario obtenerUsuario(String correo){
        try{
            UsuarioEntity usuario = usuarioRepository.findByCorreo(correo);
            if (usuario != null){
                Usuario user = new Usuario(
                    usuario.getIdUsuario(),
                    usuario.getNombre(),
                    usuario.getApPat(),
                    usuario.getApMat(),
                    usuario.getCorreo(),
                    usuario.getContrasena(),
                    usuario.getRol(),
                    usuario.getEstado()
                );
                return user;
            }return null;
        }catch(Exception e){
            return null;
        }
    }

    public String actualizarUsuario(Usuario user){
        try{
            UsuarioEntity usuario = usuarioRepository.findByCorreo(user.getCorreo());
            if (usuario != null){
                usuario.setNombre(user.getNombre());
                usuario.setApPat(user.getApPat());
                usuario.setApMat(user.getApMat());
                usuario.setContrasena(user.getContrasena());
                usuario.setRol(user.getRol());
                usuario.setEstado(user.getEstado());
                usuarioRepository.save(usuario);

                return "Usuario actualizado correctamente";
            }return "El correo no existe";
        }catch(Exception e){
            return "Error al actualizar el usuario";
        }
    }

    @Transactional
    public String eliminarUsuario(String correo){
        try{
            UsuarioEntity usuario = usuarioRepository.findByCorreo(correo);
            if (usuario != null){
                usuarioRepository.deleteByCorreo(correo);
                return "Usuario eliminado correctamente";
            }return "El correo no existe";
        }catch(Exception e){
            e.printStackTrace();
            return "Error al eliminar el usuario";
        }
    }


}

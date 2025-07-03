package com.edutech.usuarios.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edutech.usuarios.users.model.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    UsuarioEntity findByCorreo(String correo);
    Boolean existsByCorreo(String correo);
    void deleteByCorreo(String correo);

}

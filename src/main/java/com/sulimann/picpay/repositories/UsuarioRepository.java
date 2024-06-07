package com.sulimann.picpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sulimann.picpay.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}

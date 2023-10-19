package com.vamoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vamoo.entities.Usuario;

public interface  UsuarioRepository extends JpaRepository<Usuario,Long>{

}

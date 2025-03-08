package com.xandy.financaspessoais.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xandy.financaspessoais.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	boolean existsByEmail(String email);

}

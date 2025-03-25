package com.xandy.financaspessoais.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.xandy.financaspessoais.exception.RegraNegocioException;
import com.xandy.financaspessoais.model.entity.Usuario;
import com.xandy.financaspessoais.model.repository.UsuarioRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioServiceTest {

	@Autowired
	UsuarioService service;

	@Autowired
	UsuarioRepository repository;

	@Test
	public void deveValidarEmail() {
		// cenário
		repository.deleteAll();

		// ação
		service.validarEmail("email@email.com");
	}

	@Test
	public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
		// cenario
		Usuario usuario = Usuario.builder().nome("Alexandre").email("alexandre@email.com").build();
		repository.save(usuario);

		// ação
		Assertions.assertThrows(RegraNegocioException.class, () -> service.validarEmail("alexandre@email.com"));
	}
}
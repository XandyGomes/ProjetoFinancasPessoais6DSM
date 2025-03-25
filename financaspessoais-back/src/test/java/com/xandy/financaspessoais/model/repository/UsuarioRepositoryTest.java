package com.xandy.financaspessoais.model.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.xandy.financaspessoais.model.entity.Usuario;

@ExtendWith(SpringExtension.class)
//@SpringBootTest
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepositoryTest {

	@Autowired
	UsuarioRepository repository;

	@Autowired
	TestEntityManager entityManger;

	@Test
	public void deveVerificarAExistenciaDeUmEmail() {
		// cenário
		Usuario usuario = criarUsuario();
		entityManger.persist(usuario);

		// ação/execução
		boolean result = repository.existsByEmail("alexandre@email.com");

		// verificação
		Assertions.assertThat(result).isTrue();

	}

	@Test
	public void deveRetornarFalsoQuandoNãoHouverUsuarioCadastradoComOEmail() {

		// cenário
//		repository.deleteAll();

		// ação/execucação
		boolean result = repository.existsByEmail("alexandre@email.com");

		// verificação
		Assertions.assertThat(result).isFalse();
	}

	@Test
	public void devePersistirUmUsuarioNaBaseDeDados() {
		// cenario
		Usuario usuario = criarUsuario();

		// ação
		Usuario usuarioSalvo = entityManger.persist(usuario);
		;

		// verificação
		Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
	}

	@Test
	public void deveBuscarUmUsuarioPorEmail() {
		// cenario
		Usuario usuario = criarUsuario();
		entityManger.persist(usuario);

		// ação
		Optional<Usuario> result = repository.findByEmail("alexandre@email.com");

		// verificação
		Assertions.assertThat(result.isPresent()).isTrue();
	}

	@Test
	public void deveRetornarVazioAoBuscarUmUsuarioPorEmailQuandoNaoExisteNaBase() {
		// cenario

		// ação
		Optional<Usuario> result = repository.findByEmail("alexandre@email.com");

		// verificação
		Assertions.assertThat(result.isPresent()).isFalse();
	}

	public static Usuario criarUsuario() {
		return Usuario.builder().nome("Alexandre").email("alexandre@email.com").senha("1234").build();
	}
}

package com.jp.eslocapi.api.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jp.eslocapi.api.entities.EnumCategoria;
import com.jp.eslocapi.api.entities.Persona;
import com.jp.eslocapi.api.entities.Tecnico;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class ProdutorRepositoryTest {

	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	PersonaRepository repository;
	
	@BeforeEach
	public void setUp() {

	}
	@Test
	@DisplayName("Deve retornar verdadeiro quando existir um produtor na base de dados com o cpf informado")
	public void returnTrueWhenCpfExists() {
		//cenário
		String cpf = "04459471604";
		
		Persona produtor = this.createValidProdutor();
		entityManager.persist(produtor);
		
		//execução
		boolean exists = repository.existsByCpf(cpf);
		
		//verificação
		assertThat(exists).isTrue();
		
	}
	@Test
	@DisplayName("Deve retornar false quando não existir um produtor na base de dados com o cpf informado")
	public void returnFalseWhenCpfDoesntExists() {
		//cenário
		String cpf = "04459471600";
		
		Persona produtor = this.createValidProdutor();
		entityManager.persist(produtor);
		
		//execução
		boolean exists = repository.existsByCpf(cpf);
		
		//verificação
		assertThat(exists).isFalse();
		
	}
	private Persona createValidProdutor() {
		Tecnico emissor = Tecnico.builder()
				.matricula("10639")
				.registro("04459471604")
				.conselho("CFTA")
				.agente(Persona.builder().cpf("04459471604").build())
				.build();
		
		return Persona.builder()
				.cpf("04459471604")
				.nome("João Paulo")
				.cpf("04459471604")
				.cpfEmissor(emissor.getAgente().getCpf())
				.contato("33999065029")
				.categoria(EnumCategoria.AGRICULTOR_FAMILIAR)
				.build();
	}
}

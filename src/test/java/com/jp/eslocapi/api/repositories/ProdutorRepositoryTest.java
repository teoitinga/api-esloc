package com.jp.eslocapi.api.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jp.eslocapi.api.entities.EnumCategoria;
import com.jp.eslocapi.api.entities.Persona;
import com.jp.eslocapi.api.entities.Tecnico;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@Slf4j
public class ProdutorRepositoryTest {
	
	@Autowired
	PersonaRepository repository;

	private static String CPF = "04459471604";
	
	Persona produtor;
	
	@BeforeEach
	public void setUp() {

	}
	

	@AfterEach
	public void tearDown() {
		repository.deleteAll();
	}
	
	@Test
	@DisplayName("Deve salvar um registro de produtor com sucesso")
	public void saveTest() {
		//cenário
		produtor = this.createValidProdutor();

		//execução
		produtor = repository.save(produtor);
		
		//verificação
		assertThat(produtor).isNotNull();
		assertThat(produtor.getCpf()).isEqualTo("04459471604");
		
	}
	@Test
	@DisplayName("Deve retornar verdadeiro quando existir um produtor na base de dados com o cpf informado")
	public void returnTrueWhenCpfExists() {
		
		//cenário
		
		produtor = this.createValidProdutor();
		produtor = repository.save(produtor);
		
		//execução
		boolean exists = repository.existsByCpf(CPF);
		
		//verificação
		assertThat(exists).isTrue();
		
	}
	@Test
	@DisplayName("Deve retornar false quando não existir um produtor na base de dados com o cpf informado")
	public void returnFalseWhenCpfDoesntExists() {
		//cenário
		String cpf_busca = "044594716044";
		produtor = this.createValidProdutor();
		produtor = repository.save(produtor);
		
		//execução
		boolean exists = repository.existsByCpf(cpf_busca);
		
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
				.cpf(CPF)
				.nome("João Paulo")
				.cpf(CPF)
				.cpfEmissor(emissor.getAgente().getCpf())
				.contato("33999065029")
				.categoria(EnumCategoria.AGRICULTOR_FAMILIAR)
				.build();
	}
}

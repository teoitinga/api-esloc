package com.jp.eslocapi.api.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jp.eslocapi.api.entities.EnumCategoria;
import com.jp.eslocapi.api.entities.Persona;
import com.jp.eslocapi.api.entities.PropriedadeRural;
import com.jp.eslocapi.api.entities.Tecnico;
import com.jp.eslocapi.api.services.PropriedadeRuralRepository;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@Slf4j
public class PropriedadeRuralRepositoryTest {
	
	@Autowired
	PropriedadeRuralRepository repository;

	@Autowired
	PersonaRepository personaRepository;

	private static String CODIGO = "04459471604";
	
	PropriedadeRural propriedadeRural;
	
	@BeforeEach
	public void setUp() {

	}
	

	@AfterEach
	public void tearDown() {
		repository.deleteAll();
	}
	
	@Test
	@DisplayName("Deve salvar um registro de propriedade rural com sucesso")
	public void saveTest() {
		//cenário
		propriedadeRural = this.createValidPropriedadeRural();

		Persona proprietario = createValidProdutor();
		proprietario = personaRepository.save(proprietario);
		propriedadeRural.setProprietario(proprietario);
		
		//execução
		propriedadeRural = repository.save(propriedadeRural);
		
		//verificação
		assertThat(propriedadeRural).isNotNull();
		assertThat(propriedadeRural.getCodigo()).isEqualTo("04459471604");
		assertThat(propriedadeRural.getNome()).isEqualTo("Faz. Saudade II");
		assertThat(propriedadeRural.getProprietario().getCpf()).isEqualTo(createValidProdutor().getCpf());
		
	}

	
	private PropriedadeRural createValidPropriedadeRural() {
		return PropriedadeRural.builder()
				.codigo(CODIGO)
				.nome("Faz. Saudade II")
				.proprietario(createValidProdutor())
				.build();
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
				.cpfEmissor(emissor.getAgente().getCpf())
				.contato("33999065029")
				.categoria(EnumCategoria.AGRICULTOR_FAMILIAR)
				.build();
	}
}

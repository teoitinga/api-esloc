package com.jp.eslocapi.api.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jp.eslocapi.api.dto.PropriedadeRuralMinDtoPost;
import com.jp.eslocapi.api.entities.Persona;
import com.jp.eslocapi.api.entities.PropriedadeRural;
import com.jp.eslocapi.api.repositories.PersonaRepository;
import com.jp.eslocapi.api.services.impl.ProdutorServiceImpl;
import com.jp.eslocapi.api.services.impl.PropriedadeRuralServiceImpl;
import com.jp.eslocapi.core.Gerenciador;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class PropriedadeRuralServiceTest {
	@MockBean
	PropriedadeRuralService service;

	@MockBean
	PropriedadeRuralRepository repository;

	@MockBean
	PersonaRepository personaRepository;

	@MockBean
	ProdutorService produtorService;

	@MockBean
	Gerenciador gerenciador;

	@BeforeEach
	public void setUp() {
		
		this.produtorService = new ProdutorServiceImpl(personaRepository);
		
		this.service = new PropriedadeRuralServiceImpl(repository, produtorService, gerenciador);
	}
	@Test
	@DisplayName("Deve salvar o registro de atendimento")
	public void saveTest() {
		// cenário
		PropriedadeRuralMinDtoPost post = this.generateValidPropriedadeRuralDtoPost();

		// execução
		PropriedadeRuralMinDtoPost saved = this.service.save(post);

		Mockito.when(service.toPropriedadeRural(post)).thenReturn(generatePropriedadeRural());
		Mockito.when(repository.save(generatePropriedadeRuralToSave())).thenReturn(generatePropriedadeRural());

		// verificação
		org.assertj.core.api.Assertions.assertThat(saved.getCodigo()).isNotNull();
		org.assertj.core.api.Assertions.assertThat(saved.getNome()).isEqualTo(post.getNome());
		org.assertj.core.api.Assertions.assertThat(saved.getProprietarioCpf()).isEqualTo(post.getProprietarioCpf());

	}
	private Persona generateProdutorRural() {
		// TODO Auto-generated method stub
		return Persona.builder()
				.cpf("04459471604")
				.nome("Josefina de Moura Dias")
				.build();
	}
	private PropriedadeRuralMinDtoPost generateValidPropriedadeRuralDtoPost() {
		return PropriedadeRuralMinDtoPost.builder()
				.nome("Faz. da Saudade II")
				.proprietarioCpf("04459471604")
				.build();
	}
	private PropriedadeRuralMinDtoPost generateSavedPropriedadeRuralDtoPost() {
		return PropriedadeRuralMinDtoPost.builder()
				.codigo("20201004184304459471604")
				.nome("Faz. da Saudade II")
				.proprietarioCpf("04459471604")
				.build();
	}
	private PropriedadeRural generatePropriedadeRuralToSave() {
		return PropriedadeRural.builder()
				.nome("Faz. da Saudade II")
				.proprietario(Persona.builder().cpf("04459471604").build())
				.build();
	}
	private PropriedadeRural generatePropriedadeRural() {
		return PropriedadeRural.builder()
				.codigo("20201004184304459471604")
				.nome("Faz. da Saudade II")
				.proprietario(Persona.builder().cpf("04459471604").build())
				.build();
	}
}

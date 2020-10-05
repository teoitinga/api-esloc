package com.jp.eslocapi.api.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jp.eslocapi.api.dto.AtendimentoDtoPost;
import com.jp.eslocapi.api.dto.ProdutorDto;
import com.jp.eslocapi.api.dto.ProdutorMinDto;
import com.jp.eslocapi.api.entities.Persona;
import com.jp.eslocapi.api.repositories.PersonaRepository;
import com.jp.eslocapi.api.services.impl.ProdutorServiceImpl;
import com.jp.eslocapi.exceptions.BusinessException;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ProdutorServiceTest {
	ProdutorService service;
	
	@MockBean
	private PersonaRepository repository;
	
	@BeforeEach
	public void setUp() {
		this.service = new ProdutorServiceImpl(repository);
	}
	@Test
	@DisplayName("Deve salvar o registro de produtor")
	public void saveProdutor() {
		//cenário
		Persona produtor = createValidProdutor();
		
		Persona responseProdutor = Persona.builder()
				.nome("João Paulo")
				.cpf("04459471604")
				.contato("33999065029")
				.build();
		
		Mockito.when(repository.existsByCpf(Mockito.anyString())).thenReturn(false);		
		Mockito.when(repository.save(produtor)).thenReturn(responseProdutor);
		
		//execução
		Persona savedProdutor = service.save(produtor);
		
		//verificação
		assertThat(savedProdutor.getNome()).isEqualTo("João Paulo");
		assertThat(savedProdutor.getCpf()).isEqualTo("04459471604");
		assertThat(savedProdutor.getContato()).isEqualTo("33999065029");
	}
	@Test
	@DisplayName("Deve salvar o registro de produtorMinDto")
	public void saveProdutorMinDto() {
		//cenário
		ProdutorMinDto produtor = createValidProdutorMinDto();
		ProdutorDto produtorSaved = createValidProdutorDto();
		Persona produtorps = createValidProdutor();
		
		Mockito.when(repository.existsByCpf(Mockito.anyString())).thenReturn(false);		

		//execução
		ProdutorDto savedProdutor = service.saveMinDto(produtor);
		
		//verificação
		assertThat(produtorSaved.getNome()).isEqualTo("João Paulo");
		assertThat(produtorSaved.getCpf()).isEqualTo("04459471604");

	}
	private ProdutorDto createValidProdutorDto() {
		return ProdutorDto.builder()
				.nome("João Paulo")
				.cpf("04459471604")
				.build();
	}
	private Persona createValidProdutor() {
		return Persona.builder()
				.nome("João Paulo")
				.cpf("04459471604")
				.contato("33999065029")
				.build();
	}
	
	private ProdutorMinDto createValidProdutorMinDto() {
		return ProdutorMinDto.builder()
				.nome("João Paulo")
				.cpf("04459471604")
				.build();
	}
	
	@Test
	@DisplayName("Deve lançar erro de negocio ao tentar salvar um registro com cpf duplicado")
	public void shouldNotSaveProdutorWithDuplicatedCpf() {
		//cenário
		Persona produtor = createValidProdutor();
		
		String errorMessage = "Este cpf já existe";
		
		Mockito.when(repository.existsByCpf(Mockito.anyString())).thenReturn(true);
		
		//execução
		Throwable exception = Assertions.catchThrowable(()-> service.save(produtor));
		
		//verificações
		assertThat(exception).isInstanceOf(BusinessException.class)
					.hasMessage(errorMessage);
		Mockito.verify(repository, Mockito.never()).save(produtor);
		
	}
}

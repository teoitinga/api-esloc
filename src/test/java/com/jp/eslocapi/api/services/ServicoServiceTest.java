package com.jp.eslocapi.api.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jp.eslocapi.api.entities.Atendimento;
import com.jp.eslocapi.api.entities.Servico;
import com.jp.eslocapi.api.exceptions.ServiceNotFoundException;
import com.jp.eslocapi.api.repositories.ServicoRepository;
import com.jp.eslocapi.api.services.impl.ServicoServiceImpl;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@Slf4j
public class ServicoServiceTest {
	
	String COD_SERVICO = "DC";
	
	ServicoService service;

	@MockBean
	ServicoRepository repository;

	@BeforeEach
	public void setUp() {
		this.service = new ServicoServiceImpl(repository);
	}

	@Test
	@DisplayName("Deve verificar se o codigo do servico informado está registrado no banco de dados.")
	public void findByCodServicoTest() {
		// cenário

		Servico servico;

		Mockito.when(repository.findByCodigo(Mockito.anyString())).thenReturn(Optional.of(createValidServico()));

		servico = service.findByCodServico(COD_SERVICO);

		// verificações
		org.assertj.core.api.Assertions.assertThat(servico.getCodigo()).isNotNull();

	}

	@Test
	@DisplayName("Deve retornar mensagem de erro quando não encontrar o serviço informado.")
	public void returnErrorWithfindByCodServicoTest() {

		// cenário
		String errorMessage = "Serviço não registrado no banco de dados.";

		Servico servico;

		Mockito.when(repository.findByCodigo(Mockito.anyString())).thenReturn(Optional.empty());

		//execução
		Throwable exception = Assertions.catchThrowable(()-> service.findByCodServico(COD_SERVICO));

		// verificações
		assertThat(exception).isInstanceOf(ServiceNotFoundException.class)
		.hasMessage(errorMessage);
	}

	private Servico createValidServico() {
		Atendimento atd = Atendimento.builder().codigo("202010122032").build();

		return Servico.builder().codigo("DC").descricao("Dia de Campo").valorEstimado(new BigDecimal(184.45))
				.tempoEstimado(1).definicao("Realização de Dia de Campo").build();
	}

}

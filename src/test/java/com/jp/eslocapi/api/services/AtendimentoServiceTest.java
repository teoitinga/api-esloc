package com.jp.eslocapi.api.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jp.eslocapi.api.dto.AtendimentoDtoPost;
import com.jp.eslocapi.api.dto.ProdutorMinDto;
import com.jp.eslocapi.api.dto.ServicosDtoPost;
import com.jp.eslocapi.api.entities.Atendimento;
import com.jp.eslocapi.api.repositories.AtendimentoRepository;
import com.jp.eslocapi.api.services.impl.AtendimentoServiceImpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class AtendimentoServiceTest {
	
	AtendimentoService service;
	
	@MockBean
	AtendimentoRepository repository;
	
	@BeforeEach
	public void setUp() {
		this.service = new AtendimentoServiceImpl(repository);
	}
	@Test
	@DisplayName("Deve salvar o registro de atendimento")
	public void saveTest() {
		//cenário
		AtendimentoDtoPost post = this.generateAtendimentoDtoPost();
		Atendimento atdSave = generateValidAtendimento();
		//execução
		AtendimentoDtoPost savedAtendimento = this.service.save(post);
		Atendimento atdSaved = createdAtendimento();
		
		Mockito.when(repository.save(atdSave)).thenReturn(atdSaved);
		
		//verificação
		org.assertj.core.api.Assertions.assertThat(savedAtendimento.getCodigo()).isNotNull();
		org.assertj.core.api.Assertions.assertThat(savedAtendimento.getRecomendacoes()).isEqualTo(post.getRecomendacoes());
		org.assertj.core.api.Assertions.assertThat(savedAtendimento.getLocal()).isEqualTo(post.getLocal());
		
	}
	private Atendimento createdAtendimento() {
		return Atendimento.builder().build();
	}
	private Atendimento generateValidAtendimento() {
		return Atendimento.builder().build();
	}
	private AtendimentoDtoPost generateAtendimentoDtoPost() {
		
		List<ServicosDtoPost> servicos = new ArrayList<>();
		
		List<ProdutorMinDto> produtores = new ArrayList<>();
		
		ProdutorMinDto produtor_01 = ProdutorMinDto.builder()
				.cpf("79710004050")
				.nome("José Maria de Troia")
				.build();
		
		ProdutorMinDto produtor_02 = ProdutorMinDto.builder()
				.cpf("42229837052")
				.nome("Maria Filomensa de Heráclito")
				.build();
		
		produtores.add(produtor_01);
		produtores.add(produtor_02);
		
		ServicosDtoPost servico_01 = ServicosDtoPost.builder()
				.descricao("Emissão de cadastro ambiental rural")
				.valorDoServico(new BigDecimal(0))
				.valorCobrado(new BigDecimal(200.00))
				.observacoes("Ficou de pagar depois")
				.codServico("CAREM")
				.build();
		
		servicos.add(servico_01);
		
		AtendimentoDtoPost atendimento = AtendimentoDtoPost.builder()
				.codigo("20200930")
				.recomendacoes("realizar análise de solos na área pretendida.")
				.dataAtd("30/09/2020")
				.tecnico("João Paulo Santana Gusmão")
				.emissor("Sirlene Ferreira Peron")
				.local("Faz. Saudade II")
				.servicos(servicos)
				.produtores(produtores)
				.statusAtd("INICIADA")
				.build();
		return atendimento;
	}

}

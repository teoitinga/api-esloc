package com.jp.eslocapi.api.resources;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jp.eslocapi.api.dto.AtendimentoDtoPost;
import com.jp.eslocapi.api.dto.ProdutorMinDto;
import com.jp.eslocapi.api.dto.ServicosDtoPost;
import com.jp.eslocapi.api.entities.EnumLocal;
import com.jp.eslocapi.api.entities.EnumPermissao;
import com.jp.eslocapi.api.entities.GrupoServico;
import com.jp.eslocapi.api.entities.Persona;
import com.jp.eslocapi.api.entities.PropriedadeRural;
import com.jp.eslocapi.api.entities.Servico;
import com.jp.eslocapi.api.entities.ServicosAtd;
import com.jp.eslocapi.api.entities.Tecnico;
import com.jp.eslocapi.api.services.AtendimentoService;
import com.jp.eslocapi.exceptions.BusinessException;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class AtendimentoRessourceTest {
	static String API = "/api/v1/atendimentos";
	
	@Autowired
	MockMvc mvc;
	
	@MockBean
	AtendimentoService service;
	
	@Test
	@DisplayName("Deve registrar um atendimento com sucesso")
	public void createAtendimentoTest() throws Exception {
		//cenário (given)
		
		AtendimentoDtoPost dto = generateValidAtendimento();
		
		AtendimentoDtoPost savedAtendimento = createNewAtendimento();
		
		BDDMockito.given(service.save(Mockito.any(AtendimentoDtoPost.class))).willReturn(savedAtendimento);
		
		String json = new ObjectMapper().writeValueAsString(dto);
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(API)
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
		.content(json);
		
		mvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andExpect(MockMvcResultMatchers.jsonPath("codigo").isNotEmpty())
			.andExpect(MockMvcResultMatchers.jsonPath("codigo").value(dto.getCodigo()))
			.andExpect(MockMvcResultMatchers.jsonPath("recomendacoes").value(dto.getRecomendacoes()))
			.andExpect(MockMvcResultMatchers.jsonPath("statusAtd").value(dto.getStatusAtd()))
			.andExpect(MockMvcResultMatchers.jsonPath("local").value(dto.getLocal()))
			.andExpect(MockMvcResultMatchers.jsonPath("tecnico").value(dto.getTecnico()))
			.andExpect(MockMvcResultMatchers.jsonPath("emissor").value(dto.getEmissor()))
			;
		
					
	}

	@Test
	@DisplayName("Deve lançar erro se não haver produtores")
	public void createInvalidAtendimentoNoProductorsTest() throws Exception {

		//cenário (given)
		AtendimentoDtoPost dto = generateInValidAtendimentNoProdutctors();
		
		String json = new ObjectMapper().writeValueAsString(dto);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(API)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(json);
		
		mvc.perform(request)
		.andExpect(MockMvcResultMatchers.status().isBadRequest())
		.andExpect(jsonPath("errors", Matchers.hasSize(1)))

		;
		
		
	}
	@Test
	@DisplayName("Deve lançar erro se não houver serviços")
	public void createInvalidAtendimentoNoServicesTest() throws Exception {
		//cenário (given)
		
		AtendimentoDtoPost dto = generateInValidAtendimentNoServices();
		
		String json = new ObjectMapper().writeValueAsString(dto);
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(API)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(json);
		
		mvc.perform(request)
		.andExpect(MockMvcResultMatchers.status().isBadRequest())
		.andExpect(jsonPath("errors", Matchers.hasSize(1)))
		
		;
		
		
	}
	@Test
	@DisplayName("Deve lançar erro e retornar a lista de produrores com cpf's inválidos")
	public void createInvalidAtendimentoByInvalidCpfTest() throws Exception {
		//cenário (given)
		
		AtendimentoDtoPost dto = generateInValidAtendimentNoServices();
		
		String json = new ObjectMapper().writeValueAsString(dto);
		
		BDDMockito.given(service.save(Mockito.any(AtendimentoDtoPost.class))).willThrow(new BusinessException("João Paulo Santana Gusmão, Sirlene Ferreira Peron"));
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(API)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(json);
		
		mvc.perform(request)
		.andExpect(MockMvcResultMatchers.status().isBadRequest())
		.andExpect(jsonPath("errors", Matchers.hasSize(1)))
		
		;		
	}

	private AtendimentoDtoPost generateInValidAtendimentNoServices() {
		
		AtendimentoDtoPost atendimento = this.generateValidAtendimento();
		atendimento.setServicos(null);
		
		return atendimento;
	}
	private AtendimentoDtoPost generateInValidAtendimentNoProdutctors() {
		AtendimentoDtoPost atendimento = this.generateValidAtendimento();
		atendimento.setProdutores(null);
		
		return atendimento;
	}
	private AtendimentoDtoPost generateValidAtendimento() {
		
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

	private AtendimentoDtoPost createNewAtendimento() throws Exception {
		AtendimentoDtoPost response = this.generateValidAtendimento();
				response.setCodigo("20200930");
		return response;
	}

	private ServicosAtd createNewServicosAtd() {
		return ServicosAtd.builder()
				.codigo("SRV")
				.valorTotal(new BigDecimal(150000.35))
				.valorDae(new BigDecimal(548.24))
				.observacoes("Nenhuma observação")
				.descricao("Elaboração de serviços")
				.servico(createNewServico())
				.build();
	}

	private Servico createNewServico() {
		return Servico.builder()
				.codigo("SRV")
				.descricao("Serviço")
				.definicao("Prestação de serviço de qualidade")
				.tempoEstimado(3)
				.valorEstimado(new BigDecimal(245.64))
				.grupo(createNewGrupoServico())
				.build();
	}

	private GrupoServico createNewGrupoServico() {
		return GrupoServico.builder()
				.codigo("SRV01")
				.descricao("Servicos de grupo 01")
				.build();
	}

	private PropriedadeRural createNewPropriedadeRural() {
		return PropriedadeRural.builder()
				.codigo("20201909301709")
				.nome("Sitio da Saudade")
				.caracterizacao(EnumLocal.CORREGO)
				.localizacao("Cabeceira do vai e volta")
				.proprietario(this.createNewPersona())
				.areaTotal(new BigDecimal(4.84))
				.build();
	}

	private Tecnico createNewTecnico() {
		
		Persona agente = createNewPersona();
		
		return Tecnico.builder()
				.matricula("10639")
				.conselho("CFTA")
				.registro("04459471604")
				.agente(agente)
				.permissao(EnumPermissao.TECNICO)
				.build();
	}

	private Persona createNewPersona() {
		return Persona.builder()
				.cpf("04459471604")
				.nome("João Paulo")
				.build();
	}

	private ProdutorMinDto createNewProdutorMinDto() {
		return ProdutorMinDto.builder()
				.cpf("04459471604")
				.nome("João Paulo")
				.build();
	}
}

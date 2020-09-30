package com.jp.eslocapi.api.resources;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jp.eslocapi.api.dto.ProdutorMinDto;
import com.jp.eslocapi.api.entities.EnumLocal;
import com.jp.eslocapi.api.entities.EnumPermissao;
import com.jp.eslocapi.api.entities.GrupoServico;
import com.jp.eslocapi.api.entities.Persona;
import com.jp.eslocapi.api.entities.PropriedadeRural;
import com.jp.eslocapi.api.entities.Servico;
import com.jp.eslocapi.api.entities.ServicosAtd;
import com.jp.eslocapi.api.entities.Tecnico;
import com.jp.eslocapi.api.services.AtendimentoService;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class AtendimentoRessourceTest {
	static String PRODUTOR_API = "/api/v1/produtores";
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@MockBean
	AtendimentoService service;
	
	@Test
	@DisplayName("Deve registrar um atendimento com sucesso")
	public void createAtendimentoTest() throws Exception {
		//cenário (given)
		ProdutorMinDto dto;
		dto = createNewProdutorMinDto();
		
		Tecnico tecnico = createNewTecnico();
		
		PropriedadeRural propriedadeRural = createNewPropriedadeRural();
		
		Tecnico emissor = this.createNewTecnico();
		
		ServicosAtd atd = createNewServicosAtd();
		
	
					
	}

	private ServicosAtd createNewServicosAtd() {
		return ServicosAtd.builder()
				.codigo("SRV")
				.valorTotal(new BigDecimal(150000.35))
				.valorDae(new BigDecimal(548.24))
				.observacoes("Nenhuma observação")
				.descricao("Elaboração de serviços")
				.servico(crreateNewServico())
				.build();
	}

	private Servico crreateNewServico() {
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

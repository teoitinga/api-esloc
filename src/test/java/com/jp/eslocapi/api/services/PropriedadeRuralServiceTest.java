package com.jp.eslocapi.api.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
import com.jp.eslocapi.api.services.impl.PropriedadeRuralServiceImpl;
import com.jp.eslocapi.core.Gerenciador;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@Slf4j
public class PropriedadeRuralServiceTest {

	PropriedadeRuralService service;

	@MockBean
	PropriedadeRuralRepository repository;

	@MockBean
	PersonaService personaService;
	
	PropriedadeRural propriedadeRural;
	
	PropriedadeRuralMinDtoPost savedProp;
	
	@BeforeEach
	public void setUp() {
		log.info("Iniciando o metodo de teste.");
		this.service = new PropriedadeRuralServiceImpl(repository, personaService);
	}
	@Test
	@DisplayName("Deve testar a geração de códigos para rregistro no ID das classes")
	public void gerarCodigoPropriedadeTest() {
		
		//cenario
		String key = "04459471604";//cpf informado
		
		StringBuilder expectedCode = new StringBuilder();
		//obtem a data e hora atual no formato yyyyMMddhhmm
		LocalDateTime dataDoAtendimentoTime = LocalDateTime.now(); 
		
		expectedCode.append(dataDoAtendimentoTime.format(DateTimeFormatter.ofPattern("yyyyMMddhhmm")));
		
		//conctena com o cpf
		expectedCode.append(key);
		
		//execução
		String codigo = Gerenciador.GERA_IDENTIFICADOR(key);
		
		// verificação
		org.assertj.core.api.Assertions.assertThat(codigo).isNotNull();
		org.assertj.core.api.Assertions.assertThat(codigo).isEqualTo(expectedCode.toString());
	}
	@Test
	@DisplayName("Deve testar a criação de registro para PropriedadeRuralMinDtoPost com sucesso")
	public void saveTest() {
		//cenário
		PropriedadeRuralMinDtoPost post = createValidPropriedadeRuralMinDtoPost();

		Mockito.when(personaService.getByCpf(Mockito.anyString())).thenReturn(createPersona());
		Mockito.when(repository.save(Mockito.any(PropriedadeRural.class))).thenReturn(createPropriedadeRuralSaved());
		
		log.info("Verificando {}", post);
		//this.service = new PropriedadeRuralServiceImpl(repository, personaService);
		savedProp = service.save(post);

		//verificações
		org.assertj.core.api.Assertions.assertThat(this.service).isNotNull();
		org.assertj.core.api.Assertions.assertThat(savedProp).isNotNull();
		org.assertj.core.api.Assertions.assertThat(savedProp.getAreaTotal()).isEqualTo(post.getAreaTotal());	
		org.assertj.core.api.Assertions.assertThat(savedProp.getProprietarioCpf()).isEqualTo(post.getProprietarioCpf());	
	}
	@Test
	@DisplayName("Deve testar a conversão de propriedadeRural para PropriedadeRuralMinDtoPost DTO")
	public void toPropriedadeRuralMinDtoPostTest() {
		//cenário
		
		propriedadeRural = createValidPropriedadeRural();
		
		PropriedadeRuralMinDtoPost response;
	
		//execução
		response = service.toPropriedadeRuralMinDtoPost(propriedadeRural);
		
		//verificações
		org.assertj.core.api.Assertions.assertThat(response).isNotNull();
		org.assertj.core.api.Assertions.assertThat(response.getCodigo()).isEqualTo(propriedadeRural.getCodigo());
		org.assertj.core.api.Assertions.assertThat(response.getNome()).isEqualTo(propriedadeRural.getNome());
		org.assertj.core.api.Assertions.assertThat(response.getProprietarioCpf()).isEqualTo(propriedadeRural.getProprietario().getCpf());
		org.assertj.core.api.Assertions.assertThat(response.getAreaTotal()).isEqualTo(propriedadeRural.getAreaTotal());
	}
	@Test
	@DisplayName("Deve testar a conversão de PropriedadeRuralMin DTO para propriedadeRural")
	public void toPropriedadeRuralTest() {
		
		//cenário
		PropriedadeRuralMinDtoPost post = createValidPropriedadeRuralMinDtoPost();
		
		PropriedadeRural response;

		//execução
		response = service.toPropriedadeRural(post);//.toPropriedadeRuralMinDtoPost(post);
		
		//verificações
		org.assertj.core.api.Assertions.assertThat(response).isNotNull();
	}
	private Persona createPersona() {

		return Persona.builder()
				.nome("João Paulo")
				.cpf("04459471604")
				.build();
	}
	private PropriedadeRuralMinDtoPost createValidPropriedadeRuralMinDtoPost() {
		String cpf = "04459471604";
		
		return PropriedadeRuralMinDtoPost.builder()
				.codigo(Gerenciador.GERA_IDENTIFICADOR(cpf))
				.nome("Sitio Saudade II")
				.areaTotal(BigDecimal.valueOf(19.36))
				.proprietarioCpf(cpf)
				.build();
	}
	/**
	 * @param createProrpiedadeRuralSaved
	 * @return
	 */
	private PropriedadeRural createPropriedadeRuralSaved() {
		Persona proprietario = Persona.builder()
				.cpf("04459471604")
				.nome("João Paulo Santana Gusmão")
				.build();
		return PropriedadeRural.builder()
						.codigo(Gerenciador.GERA_IDENTIFICADOR(proprietario.getCpf()))
						.nome("Sitio Saudade II")
						.areaTotal(BigDecimal.valueOf(19.36))
						.proprietario(proprietario)
				.build();
	}
	private PropriedadeRural createValidPropriedadeRural() {
		Persona proprietario = Persona.builder()
				.cpf("04459471604")
				.nome("João Paulo Santana Gusmão")
				.build();
		return PropriedadeRural.builder()
						.codigo(Gerenciador.GERA_IDENTIFICADOR(proprietario.getCpf()))
						.nome("Sitio Saudade II")
						.areaTotal(BigDecimal.valueOf(19.36))
						.proprietario(proprietario)
				.build();
	}
}

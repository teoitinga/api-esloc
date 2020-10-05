package com.jp.eslocapi.api.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jp.eslocapi.api.dto.AtendimentoDtoPost;
import com.jp.eslocapi.api.dto.ProdutorMinDto;
import com.jp.eslocapi.api.dto.ServicosDtoPost;
import com.jp.eslocapi.api.entities.Atendimento;
import com.jp.eslocapi.api.entities.EnumStatus;
import com.jp.eslocapi.api.entities.Persona;
import com.jp.eslocapi.api.entities.PropriedadeRural;
import com.jp.eslocapi.api.entities.Tecnico;
import com.jp.eslocapi.api.repositories.AtendimentoRepository;
import com.jp.eslocapi.api.repositories.PersonaRepository;
import com.jp.eslocapi.api.services.impl.AtendimentoServiceImpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class AtendimentoServiceTest {

	@MockBean
	AtendimentoService service;

	@MockBean
	AtendimentoRepository repository;

	@MockBean
	PersonaRepository personaRepository;

	@MockBean
	PropriedadeRuralService propriedadeRuralService;
	
	@MockBean
	PropriedadeRuralRepository propriedadeRuralRepository;
	
	@MockBean
	PersonaService personaService;

	@BeforeEach
	public void setUp() {
		this.service = new AtendimentoServiceImpl(repository, personaService, personaRepository,
				propriedadeRuralService, propriedadeRuralRepository);
	}

	@Test
	@DisplayName("Deve salvar o registro de atendimento")
	public void saveTest() {
		// cenário
		AtendimentoDtoPost post = this.generateAtendimentoDtoPost();
		Atendimento atdSave = generateValidAtendimento();

		// execução
		this.service.save(post);
		
		AtendimentoDtoPost savedAtendimento = this.generateAtendimentoDtoPost();
		Atendimento atdSaved = createdAtendimento();

		Mockito.when(service.toAtendimento(post)).thenReturn(atdSave);
		Mockito.when(repository.save(atdSave)).thenReturn(atdSaved);
//		Mockito.when(service.toAtendimentoPost(atdSave)).thenReturn(savedAtendimento);

		// verificação
		org.assertj.core.api.Assertions.assertThat(savedAtendimento.getCodigo()).isNotNull();
		org.assertj.core.api.Assertions.assertThat(savedAtendimento.getRecomendacoes())
				.isEqualTo(post.getRecomendacoes());
		org.assertj.core.api.Assertions.assertThat(savedAtendimento.getLocal()).isEqualTo(post.getLocal());

	}

	@Test
	@DisplayName("Deve se retorna um Array com or produtores cujo CPF's são inválidos")
	public void getInvalidCpfsTest() {
		// cenário
		List<ProdutorMinDto> produtores = createListProdutoresMinDto();

		// execução
		List<ProdutorMinDto> produtoresNaoCadastrados = service.getInvalidCpfs(produtores);

		// verificação
		org.assertj.core.api.Assertions.assertThat(produtoresNaoCadastrados.size()).isEqualTo(2);
		org.assertj.core.api.Assertions.assertThat(produtoresNaoCadastrados.get(0).getNome())
				.isEqualTo("Josenildo Ferreira");
		org.assertj.core.api.Assertions.assertThat(produtoresNaoCadastrados.get(1).getNome())
				.isEqualTo("Rafael Roberto Joaquim das Neves");

	}

	@Test
	@DisplayName("Deve se retornar um Array com os produtores cujo CPF's estão registrados no banco de dados")
	public void getExistentProductorsTest() {
		// cenário
		List<ProdutorMinDto> produtores = createListProdutoresMinDto();

		// execução
		List<ProdutorMinDto> produtoresNaoCadastrados = service.getExistentProductors(produtores);

		// verificação
		org.assertj.core.api.Assertions.assertThat(produtoresNaoCadastrados.size()).isEqualTo(0);

	}

	@Test
	@DisplayName("Deve se retornar um Array com os produtores cujo CPF's ainda não estão registrados no banco de dados")
	public void getNotExistentProductorsTest() {
		// cenário
		List<ProdutorMinDto> produtores = createListProdutoresMinDto();

		// execução
		List<ProdutorMinDto> produtoresNaoCadastrados = service.getNotExistentProductors(produtores);

		// verificação
		org.assertj.core.api.Assertions.assertThat(produtoresNaoCadastrados.size()).isEqualTo(11);

	}

	@Test
	@DisplayName("Deve se retorna um Array com or produtores cujo CPF's são inválidos")
	public void getCpfsTest() {
		// cenário
		List<ProdutorMinDto> produtores = createListProdutoresMinDto();

		// execução
		List<ProdutorMinDto> produtoresNaoCadastrados = service.getValidCpfs(produtores);

		// verificação
		org.assertj.core.api.Assertions.assertThat(produtoresNaoCadastrados.size()).isEqualTo(9);
		org.assertj.core.api.Assertions.assertThat(produtoresNaoCadastrados.get(0).getNome())
				.isEqualTo("Larissa Nair Almada");
		org.assertj.core.api.Assertions.assertThat(produtoresNaoCadastrados.get(8).getNome())
				.isEqualTo("Augusto Rodrigo Fernandes");

	}

	@Test
	@DisplayName("Deve verificar se o produtor informado pelo CPF já está cadastrado")
	public void isRegistered() {
		// cenário
		String cpf = "12312332424212300";

		// execução

		Boolean isRegistered = service.isRegistered(cpf);

		// verificação
		org.assertj.core.api.Assertions.assertThat(isRegistered).isNotNull();
		org.assertj.core.api.Assertions.assertThat(isRegistered).isFalse();
	}

	private List<ProdutorMinDto> createListProdutoresMinDto() {
		List<ProdutorMinDto> produtores = new ArrayList<>();
		produtores.add(ProdutorMinDto.builder().cpf("985.695.179-80").nome("Larissa Nair Almada").build());// valido
		produtores.add(ProdutorMinDto.builder().cpf("534.862.091-24").nome("Lorenzo Luiz Nascimento").build());// valido
		produtores.add(ProdutorMinDto.builder().cpf("204.197.222-03").nome("Cláudia Ayla Elza Vieira").build());// valido
		produtores.add(ProdutorMinDto.builder().cpf("299.922.660-82").nome("Fábio Igor André Barros").build());// valido
		produtores.add(ProdutorMinDto.builder().cpf("11122233321").nome("Josenildo Ferreira").build());// invalido
		produtores.add(ProdutorMinDto.builder().cpf("850.228.220-40").nome("Oliver Tomás da Costa").build());// valido
		produtores.add(ProdutorMinDto.builder().cpf("576.608.327-00").nome("Kamilly Gabriela da Paz").build());// valido
		produtores
				.add(ProdutorMinDto.builder().cpf("380.450.918-51").nome("Gabriel Julio Marcos Vinicius Melo").build());// valido
		produtores.add(ProdutorMinDto.builder().cpf("150.699.308-77").nome("Guilherme Renan Assis").build());// valido
		produtores.add(ProdutorMinDto.builder().cpf("17201683691").nome("Augusto Rodrigo Fernandes").build());// valido
		produtores.add(ProdutorMinDto.builder().cpf("084459471604").nome("Rafael Roberto Joaquim das Neves").build());// invalido

		return produtores;
	}

	@Test
	@DisplayName("Deve lancar erro ao registrar atendimento para produrtores com Cpf's invalidos")
	public void saveInvalidCPfsTest() {
		// cenário
		AtendimentoDtoPost post = this.generateAtendimentoDtoPost();
		Atendimento atdSave = generateValidAtendimento();

		// execução
		AtendimentoDtoPost savedAtendimento = this.service.save(post);
		Atendimento atdSaved = createdAtendimento();

		List<ProdutorMinDto> invalidCpfs = this.createInvalidCpfs();

		Mockito.when(repository.save(atdSave)).thenReturn(atdSaved);

		// verificação
		org.assertj.core.api.Assertions.assertThat(atdSaved.getCodigo()).isNotNull();
		org.assertj.core.api.Assertions.assertThat(atdSaved.getRecomendacoes()).isEqualTo(post.getRecomendacoes());
		org.assertj.core.api.Assertions.assertThat(atdSaved.getPropriedadeRural().getNome()).isEqualTo(post.getLocal());

	}

	@Test
	@DisplayName("Testa se um CPF é invalido")
	public void cpfNoValidTest() {
		// cenário
		String cpf = "12312312300";

		// execução
		boolean cpfValid = service.cpfValid(cpf);

		List<ProdutorMinDto> invalidCpfs = this.createInvalidCpfs();
		Boolean cpfValido = service.cpfValid(cpf);

		// verificação
		org.assertj.core.api.Assertions.assertThat(cpfValid).isNotNull();
		org.assertj.core.api.Assertions.assertThat(cpfValid).isFalse();

	}

	@Test
	@DisplayName("Testa se um CPF é válido")
	public void cpfValidTest() {
		// cenário
		String cpf = "04459471604";

		// execução
		boolean cpfValid = service.cpfValid(cpf);

		List<ProdutorMinDto> invalidCpfs = this.createInvalidCpfs();
		Boolean cpfValido = service.cpfValid(cpf);

		// verificação
		org.assertj.core.api.Assertions.assertThat(cpfValid).isNotNull();
		org.assertj.core.api.Assertions.assertThat(cpfValid).isTrue();

	}

	private List<ProdutorMinDto> createInvalidCpfs() {
		ArrayList<ProdutorMinDto> produtores = new ArrayList<>();

		produtores.add(ProdutorMinDto.builder().cpf("12300099823").build());
		produtores.add(ProdutorMinDto.builder().cpf("111112223455").build());

		return produtores;
	}

	private Atendimento createdAtendimento() {
		return generateValidAtendimento();
	}

	private Atendimento generateValidAtendimento() {
		
		LocalDate atendimentoData = LocalDate.now();
		
		List<ServicosDtoPost> servicos = new ArrayList<>();

		List<ProdutorMinDto> produtores = new ArrayList<>();

		ProdutorMinDto produtor_01 = ProdutorMinDto.builder().cpf("79710004050").nome("José Maria de Troia").build();

		ProdutorMinDto produtor_02 = ProdutorMinDto.builder().cpf("42229837052").nome("Maria Filomensa de Heráclito")
				.build();

		produtores.add(produtor_01);
		produtores.add(produtor_02);

		ServicosDtoPost servico_01 = ServicosDtoPost.builder().descricao("Emissão de cadastro ambiental rural")
				.valorDoServico(new BigDecimal(0)).valorCobrado(new BigDecimal(200.00))
				.observacoes("Ficou de pagar depois").codServico("CAREM").build();

		servicos.add(servico_01);
		Persona agente = Persona.builder()
				.cpf("04459471604")
				.nome("João Paulo santana Gusmão")
				.build();
		
		Tecnico responsavelTecnico = Tecnico.builder()
				.agente(agente )
				.conselho("CFTA")
				.registro("04459471604")
				.build();
		PropriedadeRural propriedadeRural = PropriedadeRural.builder()
				.areaTotal(new BigDecimal(54.46))
				.emissor(responsavelTecnico)
				.nome("Faz. Saudade II")
				.proprietario(agente)
				.build();
		return Atendimento.builder()
				.codigo("20200930")
				.recomendacoes("realizar análise de solos na área pretendida.")
				.atendimentoData(atendimentoData)
				.responsavelTecnico(responsavelTecnico )
				.emissor(responsavelTecnico)
				.propriedadeRural(propriedadeRural )
				.status(EnumStatus.INICIADA)

				.build();
	}

	private AtendimentoDtoPost generateAtendimentoDtoPost() {

		List<ServicosDtoPost> servicos = new ArrayList<>();

		List<ProdutorMinDto> produtores = new ArrayList<>();

		ProdutorMinDto produtor_01 = ProdutorMinDto.builder().cpf("79710004050").nome("José Maria de Troia").build();

		ProdutorMinDto produtor_02 = ProdutorMinDto.builder().cpf("42229837052").nome("Maria Filomensa de Heráclito")
				.build();

		produtores.add(produtor_01);
		produtores.add(produtor_02);

		ServicosDtoPost servico_01 = ServicosDtoPost.builder().descricao("Emissão de cadastro ambiental rural")
				.valorDoServico(new BigDecimal(0)).valorCobrado(new BigDecimal(200.00))
				.observacoes("Ficou de pagar depois").codServico("CAREM").build();

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

package com.jp.eslocapi.api.services.impl;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jp.eslocapi.api.dto.AtendimentoDtoPost;
import com.jp.eslocapi.api.dto.AtendimentoInfoDto;
import com.jp.eslocapi.api.dto.ProdutorMinDto;
import com.jp.eslocapi.api.dto.ServicosDtoPost;
import com.jp.eslocapi.api.entities.Atendimento;
import com.jp.eslocapi.api.entities.EnumCategoria;
import com.jp.eslocapi.api.entities.EnumConfirm;
import com.jp.eslocapi.api.entities.EnumStatus;
import com.jp.eslocapi.api.entities.Persona;
import com.jp.eslocapi.api.entities.PropriedadeRural;
import com.jp.eslocapi.api.entities.Servico;
import com.jp.eslocapi.api.entities.ServicosAtd;
import com.jp.eslocapi.api.entities.Tecnico;
import com.jp.eslocapi.api.exceptions.NoProductorsException;
import com.jp.eslocapi.api.exceptions.NotListProductorsException;
import com.jp.eslocapi.api.repositories.AtendimentoRepository;
import com.jp.eslocapi.api.repositories.PersonaRepository;
import com.jp.eslocapi.api.repositories.ServicoRepository;
import com.jp.eslocapi.api.repositories.TecnicoRespository;
import com.jp.eslocapi.api.services.AtendimentoService;
import com.jp.eslocapi.api.services.PersonaService;
import com.jp.eslocapi.api.services.PropriedadeRuralRepository;
import com.jp.eslocapi.api.services.PropriedadeRuralService;
import com.jp.eslocapi.api.services.ServicoService;
import com.jp.eslocapi.api.services.TecnicoService;
import com.jp.eslocapi.core.Gerenciador;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AtendimentoServiceImpl implements AtendimentoService {

	private AtendimentoRepository repository;
	
	private PersonaService personaService;
	
	private TecnicoService tecnicoService;
	
	private PropriedadeRuralService propriedadeRuralService;

	private ServicoService servicoService;
	
	public AtendimentoServiceImpl(
			AtendimentoRepository repository,
			//PersonaService personaService, 
			PersonaRepository personaRepository,
			//PropriedadeRuralService propriedadeRuralService,
			PropriedadeRuralRepository propriedadeRuralRepository,
			//ServicoService servicoService,
			ServicoRepository servicoRepository,
			//TecnicoService tecnicoService,
			TecnicoRespository tecnicoRepository
			) {

		this.repository = repository;
		
		this.personaService = new PersonaServiceImpl(personaRepository);
		
		this.propriedadeRuralService = new PropriedadeRuralServiceImpl(
												propriedadeRuralRepository,
												this.personaService);
		
		this.servicoService = new ServicoServiceImpl(servicoRepository);
		
		this.tecnicoService = new TecnicoServiceImpl(tecnicoRepository);
	}

	@Override
	@Transactional
	public AtendimentoDtoPost save(AtendimentoDtoPost atd) {
		Atendimento atendimento = this.toAtendimento(atd);

		//
		Persona agente = Persona.builder()
				.cpf("04459471604")
				.nome("João Paulo")
				.categoria(EnumCategoria.OUTROS)
				.build();
		Tecnico emissor = tecnicoService.getByMatricula("10639");
		
		atendimento.setEmissor(emissor);
		
		//01 - verifica integridade dos produtores a se cadastrar
		if(atd.getProdutores()==null || atd.getProdutores().size() < 1) {
			throw new NoProductorsException();
		}
		////////01.1 - PARA CADA PRODUTOR DO ARRAY
		List<ProdutorMinDto> validProdutors = this.getValidCpfs(atd.getProdutores());
		List<ProdutorMinDto> invalidProdutors = this.getInvalidCpfs(atd.getProdutores());
		
		////////01.1 - VERIFICA SE JÁ EXISTE O CPF REGISTRADO
		List<ProdutorMinDto> existentProdutors = this.getExistentProductors(validProdutors);
		List<ProdutorMinDto> noExistentProdutors = this.getNotExistentProductors(validProdutors);
		////////01.2 - SE NÃO EXISTE, VERIFICA DE ESTÁ CORRETO
		
		////////01.2.3 - SE ESTÁ CORRETO, REGISTRA NO BD - os produtores não exitentes no banco de dados(noExistentProdutors)
		List<Persona> savedProdutores = noExistentProdutors.stream().map(productor->this.registerProductors(productor))
				.collect(Collectors.toList());
		List<Persona> infoProductors = existentProdutors.stream().map(productor->this.personaService.toPersona(productor)).collect(Collectors.toList());
		log.info("existentProdutors: {}", existentProdutors);
		log.info("noExistentProdutors: {}", noExistentProdutors);
		infoProductors.addAll(savedProdutores);
		log.info("infoProductors: {}", infoProductors);
		
		atendimento.setProdutores(infoProductors);
		////////01.2.4 - SE NÃO ESTÁ CORRETO, ARMAZENA NA VARIAVEL DE RETORNO
		if(invalidProdutors.size()>0) {
			//retorna para o usuario os produtores que não foram registrados
		}
		

		
		//02 - Obtem o código do atendimento e configura o valor
		///////Armazena o cpf do primeiro produtor informado
		
		String codigoDoAtendimento = this.geraIdentificador(atd.getProdutores().get(0).getCpf());
		atendimento.setCodigo(codigoDoAtendimento);	
		
		//03 - verifica integridade da propriedade rural
		////////02.1 - SE NÃO EXISTE, FAZ O REGISTRO NO BANCO DE DADOS EM NOME DO PRODUTOR INDEX(0) - PRIMEIRO PRODUTOR DA LISTA
		//////// pesquisa a propriedade rural com o nome informado e que seja de propriedade do progutor informado
		if(infoProductors.size()==0 || infoProductors == null) {
			throw new NotListProductorsException();
		}
		Persona proprietario = infoProductors.get(0);
		String nomeDaPropriedade = atd.getLocal();
		
		PropriedadeRural propriedade = this.propriedadeRuralService.findPropriedadeRural(proprietario, nomeDaPropriedade);
		
		if(propriedade == null) {
			

			propriedade = PropriedadeRural.builder()
					.codigo(codigoDoAtendimento)
					.proprietario(proprietario)
					.nome(nomeDaPropriedade)
					.emissor(emissor)
					.build();
			
					propriedade = this.propriedadeRuralService.save(propriedade);
		}
		
		atendimento.setPropriedadeRural(propriedade);


		
		//04 - verifica integridade dos servicos a se registrar
		List<ServicosAtd> servicosARegistrar;
		List<ServicosAtd> servicosValidos = atd.getServicos().stream().map(servico->toServicosAtd(servico)).collect(Collectors.toList());
		
		atendimento.setServicos(servicosValidos);
		
		//05 - verifica integridade das recomendações
		String recomendaoces = null;
		
		atendimento.setRecomendacoes(recomendaoces);
		
		//06 - verifica integridade da data do atendimentos
		LocalDate dataAtendimento = LocalDate.now();
		
		atendimento.setAtendimentoData(dataAtendimento);
		
		//07 - verifica integridade do responsável técnico
		Tecnico responsavel = null;
		
		atendimento.setResponsavelTecnico(responsavel);
		
		//08 - verifica integridade do emissor
		emissor = Tecnico.builder()
				.agente(proprietario)
				.build();
		
		atendimento.setEmissor(emissor);
		
		//09 - por ser uma inserção, set a flag tornar publico como NAO
		EnumConfirm publicar = EnumConfirm.NAO;
		
		atendimento.setPublico(publicar);
		
		//10- por ser uma insserção, seta o status do atendimentos como INICIADA
		EnumStatus status = EnumStatus.INICIADA;
		
		atendimento.setStatus(status);

		//registra no banco de dados

		
		Atendimento savedAtendimento = repository.save(atendimento);


		return toAtendimentoPost(savedAtendimento);
	}
	
	@Override
	public ServicosAtd toServicosAtd(ServicosDtoPost servico) {

		Servico srv = servicoService.findByCodServico(servico.getCodServico());

		return ServicosAtd.builder()
				.codigo(servico.getCodServico())
				.descricao(servico.getDescricao())
				.observacoes(servico.getObservacoes())
				.servico(srv)
				.build();
	}

	private Persona registerProductors(ProdutorMinDto productor) {
		Persona saved;
		saved = this.personaService.save(productor);
		return saved;
	}

	@Override
	public List<ProdutorMinDto> getNotExistentProductors(List<ProdutorMinDto> validProdutors) {
		List<ProdutorMinDto> noExistentProdutors = validProdutors.stream()
                .filter(produtor->!isRegistered(produtor.getCpf()))
                .collect(Collectors.toList());
		
		
		return noExistentProdutors;
	}

	@Override
	public boolean isRegistered(String cpf) {

		if(personaService.existsCpf(cpf)) {
			return true;
		};
		
		return false;
	}

	@Override
	public List<ProdutorMinDto> getExistentProductors(List<ProdutorMinDto> validProdutors) {
		List<ProdutorMinDto> existentProdutors = validProdutors.stream()
                .filter(produtor->isRegistered(produtor.getCpf()))
                .collect(Collectors.toList());
		
		
		return existentProdutors;
	}
	@Override
	public Atendimento toAtendimento(AtendimentoDtoPost atd) {
		return Atendimento.builder().build();
	}
	
	@Override
	public AtendimentoDtoPost toAtendimentoPost(Atendimento atd) {
		return AtendimentoDtoPost.builder()
				.build();
	}

	@Override
	public List<ProdutorMinDto> getInvalidCpfs(List<ProdutorMinDto> produtores) {
	
		List<ProdutorMinDto> produtoresWithInvalidCpf = produtores.stream()
                .filter(produtor->!cpfValid(produtor.getCpf()))
                .collect(Collectors.toList());
		
		
		return produtoresWithInvalidCpf;
	}
	@Override
	public List<ProdutorMinDto> getValidCpfs(List<ProdutorMinDto> produtores) {
		
		List<ProdutorMinDto> produtoresWithInvalidCpf = produtores.stream()
				.filter(produtor->cpfValid(produtor.getCpf()))
				.collect(Collectors.toList());
		
		
		return produtoresWithInvalidCpf;
	}

	@Override
	public boolean cpfValid(String cpf) {
		
		cpf = cpf.replace('.',' ');
		cpf = cpf.replace('-',' ');
		cpf = cpf.replaceAll(" ","");
		// considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (cpf.equals("00000000000") ||
            cpf.equals("11111111111") ||
            cpf.equals("22222222222") || cpf.equals("33333333333") ||
            cpf.equals("44444444444") || cpf.equals("55555555555") ||
            cpf.equals("66666666666") || cpf.equals("77777777777") ||
            cpf.equals("88888888888") || cpf.equals("99999999999") ||
            (cpf.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
        // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
        // converte o i-esimo caractere do CPF em um numero:
        // por exemplo, transforma o caractere '0' no inteiro 0
        // (48 eh a posicao de '0' na tabela ASCII)
            num = (int)(cpf.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

        // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
            num = (int)(cpf.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                 dig11 = '0';
            else dig11 = (char)(r + 48);

        // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
                 return(true);
            else return(false);
                } catch (InputMismatchException erro) {
                return(false);
            }
	}

	@Override
	public String geraIdentificador(String cpf) {
		return Gerenciador.GERA_IDENTIFICADOR(cpf);
	}

	@Override
	public List<AtendimentoInfoDto> getAtendimentos() {
		List<Atendimento> atds = repository.findAll();
		
		return atds.stream().map(atd->toListAtendimentoInfoDto(atd)).collect(Collectors.toList());
	}

	private AtendimentoInfoDto toListAtendimentoInfoDto(Atendimento atd) {
		return toAtendimentoInfoDto(atd);
	}

	private AtendimentoInfoDto toAtendimentoInfoDto(Atendimento atd) {
		return AtendimentoInfoDto.builder()
				.codigo(atd.getCodigo())
				.recomendacoes(atd.getRecomendacoes())
				.build();
	}


}

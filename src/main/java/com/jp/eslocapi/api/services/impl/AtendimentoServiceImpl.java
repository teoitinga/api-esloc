package com.jp.eslocapi.api.services.impl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jp.eslocapi.api.dto.AtendimentoDtoPost;
import com.jp.eslocapi.api.dto.ProdutorMinDto;
import com.jp.eslocapi.api.entities.Atendimento;
import com.jp.eslocapi.api.entities.Persona;
import com.jp.eslocapi.api.exceptions.NoProductorsException;
import com.jp.eslocapi.api.repositories.AtendimentoRepository;
import com.jp.eslocapi.api.repositories.PersonaRepository;
import com.jp.eslocapi.api.services.AtendimentoService;
import com.jp.eslocapi.api.services.PersonaService;

@Service
public class AtendimentoServiceImpl implements AtendimentoService {

	private AtendimentoRepository repository;
	
	private PersonaService personaService;

	public AtendimentoServiceImpl(
			AtendimentoRepository repository,
			PersonaService personaService, 
			PersonaRepository personaRepository) {

		this.repository = repository;
		
		this.personaService = new PersonaServiceImpl(personaRepository);
	}

	@Override
	public AtendimentoDtoPost save(AtendimentoDtoPost atd) {
		
		Atendimento atendimento = this.toAtendimento(atd);
		
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
		
		////////01.2.4 - SE NÃO ESTÁ CORRETO, ARMAZENA NA VARIAVEL DE RETORNO
		if(invalidProdutors.size()>0) {
			//retorna para o usuario os produtores que não foram registrados
		}
		
		//02 - verifica integridade da propriedade rural
		////////02.1 - SE NÃO EXISTE, FAZ O REGISTRO NO BANCO DE DADOS EM NOME DO PRODUTOR INDEX(0) - PRIMEIRO PRODUTOR DA LISTA
		
		
		//03 - verifica integridade dos servicos a se registrar
		
		//04 - verifica integridade das recmoendações
		
		//05 - verifica integridade da data do atendimentos
		
		//06 - verifica integridade do responsável técnico
		
		//07 - verifica integridade do emissor
		
		//07 - por ser uma insercção, set a flag tornar publico como NAO
		
		
		//07- por ser uma insserção, seta o status do atendimentos como INICIADA
		
		
		
		//obtem o código do atendimento
		
		//registra no banco de dados
		return toAtendimentoPost(repository.save(atendimento));
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
		Optional<Persona> produtor = repository.findByCpf(cpf);
		if(produtor.isPresent()) {
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


}

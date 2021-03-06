package com.jp.eslocapi.api.services.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.jp.eslocapi.api.dto.ProdutorDto;
import com.jp.eslocapi.api.dto.ProdutorMinDto;
import com.jp.eslocapi.api.entities.Persona;
import com.jp.eslocapi.api.exceptions.ProdutorNotFoundException;
import com.jp.eslocapi.api.repositories.PersonaRepository;
import com.jp.eslocapi.api.services.ProdutorService;
import com.jp.eslocapi.exceptions.BusinessException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProdutorServiceImpl implements ProdutorService {

	private PersonaRepository repository;

	public ProdutorServiceImpl(PersonaRepository repository) {
		this.repository = repository;
	}

	@Override
	public Persona save(Persona produtor) {
		if(this.repository.existsByCpf(produtor.getCpf())) {
			throw new BusinessException("Este cpf já existe");
		}
		return repository.save(produtor);
	}
	@Override
	public ProdutorDto saveMinDto(ProdutorMinDto produtorMinDto) {

		if(this.repository.existsByCpf(produtorMinDto.getCpf())) {
			throw new BusinessException("Este cpf já existe");
		}

		Persona produtor = toProdutor(produtorMinDto);

		produtor = repository.save(produtor);

		return this.toProdutorDto(produtor);
	}

	@Override
	public Persona toProdutor(ProdutorMinDto produtorMinDto) {

		return Persona.builder().cpf(produtorMinDto.getCpf()).nome(produtorMinDto.getNome()).build();
	}

	@Override
	public Persona getByCpf(String cpf) {
		return repository.findByCpf(cpf).orElseThrow(()-> new ProdutorNotFoundException());
	}

	@Override
	public void delete(Persona toDeleted) {
		if(this.repository.existsByCpf(toDeleted.getCpf())) {
			throw new BusinessException("Este registro não existe.");
		}
		repository.delete(toDeleted);
	}

	@Override
	public ProdutorDto update(ProdutorDto dto) {
		return null;
	}

	/** Obtem uma inância de <b>Produtor</b> a partir de uma de <b>ProdutorDto</b> informada.
	 * @author João Paulo Santana Gusmão
	 * @param produtorDto
	 * @return Produtor 
	 * @see Persona
	 */
	@Override
	public Persona toProdutor(ProdutorDto produtorDto) {
		LocalDate dataNascimento;
		
		try {
			dataNascimento = LocalDate.parse(produtorDto.getDataNascimento(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			return Persona.builder()
					.nome(produtorDto.getNome())
					.cpf(produtorDto.getCpf())
					.contato(produtorDto.getFone())
					.nascimento(dataNascimento)
					.build();
		}catch(Exception e) {
			return null;
		}
	}
	
	/** Obtem uma instância de <b>ProdutorDto</b> a partir de uma de <b>Produtor</b> informada.
	 * @author João Paulo Santana Gusmão
	 * @param produtor
	 * @return ProdutorDto 
	 * @see ProdutorDto
	 */
	@Override
	public ProdutorDto toProdutorDto(Persona toSaved) {
		String dataNascimento;
		try{
			dataNascimento = String.valueOf(toSaved.getNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			return ProdutorDto.builder()
					.nome(toSaved.getNome())
					.cpf(toSaved.getCpf())
					.fone(toSaved.getContato())
					.dataNascimento(dataNascimento)
					.build();
		}catch(Exception e) {
			return null;
		}
		
	}

}

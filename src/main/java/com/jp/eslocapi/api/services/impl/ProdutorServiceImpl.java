package com.jp.eslocapi.api.services.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.jp.eslocapi.api.dto.ProdutorDto;
import com.jp.eslocapi.api.entities.Persona;
import com.jp.eslocapi.api.exceptions.ProdutorNotFoundException;
import com.jp.eslocapi.api.repositories.PersonaRepository;
import com.jp.eslocapi.api.services.ProdutorService;
import com.jp.eslocapi.exceptions.BusinessException;

@Service
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
		return Persona.builder()
				.nome(produtorDto.getNome())
				.cpf(produtorDto.getCpf())
				.contato(produtorDto.getFone())
				.nascimento(LocalDate.parse(produtorDto.getDataNascimento(), DateTimeFormatter.ofPattern("dd/MM/yyyy")))
				.build();
	}
	
	/** Obtem uma inância de <b>ProdutorDto</b> a partir de uma de <b>Produtor</b> informada.
	 * @author João Paulo Santana Gusmão
	 * @param produtor
	 * @return ProdutorDto 
	 * @see ProdutorDto
	 */
	@Override
	public ProdutorDto toProdutorDto(Persona toSaved) {
		return ProdutorDto.builder()
				.nome(toSaved.getNome())
				.cpf(toSaved.getCpf())
				.fone(toSaved.getContato())
				.dataNascimento(String.valueOf(toSaved.getNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))))
				.build();
	}

}

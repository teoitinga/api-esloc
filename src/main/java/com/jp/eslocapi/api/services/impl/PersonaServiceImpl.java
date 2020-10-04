package com.jp.eslocapi.api.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jp.eslocapi.api.dto.ProdutorMinDto;
import com.jp.eslocapi.api.entities.EnumCategoria;
import com.jp.eslocapi.api.entities.Persona;
import com.jp.eslocapi.api.repositories.PersonaRepository;
import com.jp.eslocapi.api.services.PersonaService;
@Service
public class PersonaServiceImpl implements PersonaService {
	
	PersonaRepository repository;

	public PersonaServiceImpl(PersonaRepository personaRepository) {

		this.repository = personaRepository;
	}

	@Override
	public Persona save(ProdutorMinDto productor) {
		Persona produtor = toPersona(productor);
		
		return this.repository.save(produtor);
	}

	private Persona toPersona(ProdutorMinDto productor) {
		return Persona.builder()
				.cpf(productor.getCpf())
				.nome(productor.getNome())
				.categoria(EnumCategoria.AGRICULTOR_FAMILIAR)
				.build();
	}

	@Override
	public Optional<Persona> getByCpf(String cpf) {
		return repository.findByCpf(cpf);
	}

}

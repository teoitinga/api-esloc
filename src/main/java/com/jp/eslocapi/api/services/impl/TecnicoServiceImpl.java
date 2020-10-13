package com.jp.eslocapi.api.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jp.eslocapi.api.entities.Tecnico;
import com.jp.eslocapi.api.exceptions.TecnicoNotFoundException;
import com.jp.eslocapi.api.repositories.TecnicoRespository;
import com.jp.eslocapi.api.services.TecnicoService;

@Service
public class TecnicoServiceImpl implements TecnicoService {

	private TecnicoRespository repository;

	public TecnicoServiceImpl(TecnicoRespository repository) {
		this.repository = repository;
	}

	@Override
	public Tecnico getByMatricula(String matricula) {
		Optional<Tecnico> tecnico = this.repository.getByMatricula(matricula);
		
		if(!tecnico.isPresent()) {
			throw new TecnicoNotFoundException();
		}
		return tecnico.get();
	}

}

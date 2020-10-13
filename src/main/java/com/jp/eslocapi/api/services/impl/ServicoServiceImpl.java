package com.jp.eslocapi.api.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jp.eslocapi.api.entities.Servico;
import com.jp.eslocapi.api.exceptions.ServiceNotFoundException;
import com.jp.eslocapi.api.repositories.ServicoRepository;
import com.jp.eslocapi.api.services.ServicoService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServicoServiceImpl implements ServicoService {

	private ServicoRepository repository;

	public ServicoServiceImpl(ServicoRepository repository) {
		this.repository = repository;
	}

	@Override
	public Servico findByCodServico(String codServico) {
		
		Optional<Servico> servico = repository.findByCodigo(codServico);

		if(!servico.isPresent()) {
			throw new ServiceNotFoundException();
		}
		return servico.get();
	}

	@Override
	public Servico save(Servico servico) {
		return repository.save(servico);
	}

}

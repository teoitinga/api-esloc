package com.jp.eslocapi.api.services.impl;

import org.springframework.stereotype.Service;

import com.jp.eslocapi.api.dto.AtendimentoDtoPost;
import com.jp.eslocapi.api.entities.Atendimento;
import com.jp.eslocapi.api.repositories.AtendimentoRepository;
import com.jp.eslocapi.api.services.AtendimentoService;

@Service
public class AtendimentoServiceImpl implements AtendimentoService {

	private AtendimentoRepository repository;

	public AtendimentoServiceImpl(AtendimentoRepository repository) {

		this.repository = repository;
	}

	@Override
	public AtendimentoDtoPost save(AtendimentoDtoPost atd) {
		Atendimento atendimento = toAtendimento(atd);
		return toAtendimentoPost(repository.save(atendimento));
	}

	private Atendimento toAtendimento(AtendimentoDtoPost atd) {
		return Atendimento.builder().build();
	}
	private AtendimentoDtoPost toAtendimentoPost(Atendimento atd) {
		return AtendimentoDtoPost.builder()
				.build();
	}

}

package com.jp.eslocapi.api.services.impl;

import org.springframework.stereotype.Service;

import com.jp.eslocapi.api.dto.PropriedadeRuralMinDtoPost;
import com.jp.eslocapi.api.entities.Persona;
import com.jp.eslocapi.api.entities.PropriedadeRural;
import com.jp.eslocapi.api.services.PersonaService;
import com.jp.eslocapi.api.services.PropriedadeRuralRepository;
import com.jp.eslocapi.api.services.PropriedadeRuralService;
import com.jp.eslocapi.core.Gerenciador;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PropriedadeRuralServiceImpl implements PropriedadeRuralService {

	PropriedadeRuralRepository repository;

	PersonaService personaService;

	public PropriedadeRuralServiceImpl(PropriedadeRuralRepository repository, PersonaService personaService) {
		this.repository = repository;
		this.personaService = personaService;
	}

	@Override
	public PropriedadeRuralMinDtoPost save(PropriedadeRuralMinDtoPost post) {
		log.info("Log save {}", post);
		
		post.setCodigo(gerarCodigoPropriedade(post.getProprietarioCpf()));
		
		PropriedadeRural propriedade = toPropriedadeRural(post);
		
		PropriedadeRural saved = repository.save(propriedade);
		
		return toPropriedadeRuralMinDtoPost(saved);
	}

	@Override
	public String gerarCodigoPropriedade(String key) {
		return Gerenciador.GERA_IDENTIFICADOR(key);
	}

	@Override
	public PropriedadeRuralMinDtoPost toPropriedadeRuralMinDtoPost(PropriedadeRural saved) {
		log.info("Log toPropriedadeRuralMinDtoPost {}", saved);
		return PropriedadeRuralMinDtoPost.builder()
				.codigo(saved.getCodigo())
				.nome(saved.getNome())
				.proprietarioCpf(saved.getProprietario().getNome())
				.build();
	}

	public boolean exists(PropriedadeRural propriedadde) {
		// Confere com o nome e o proprietario
		return true;
	}

	@Override
	public PropriedadeRural toPropriedadeRural(PropriedadeRuralMinDtoPost post) {
		Persona proprietario = personaService.getByCpf(post.getProprietarioCpf());

		return PropriedadeRural.builder().nome(post.getNome()).proprietario(proprietario).build();
	}

	@Override
	public PropriedadeRural findPropriedadeRural(Persona proprietario, String nomeDaPropriedade) {
		return repository.findByNomeAndProprietario(nomeDaPropriedade, proprietario);
	}

	@Override
	public PropriedadeRural save(PropriedadeRural propriedade) {
		return repository.save(propriedade);
	}

}

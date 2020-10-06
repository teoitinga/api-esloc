package com.jp.eslocapi.api.services.impl;

import org.springframework.stereotype.Service;

import com.jp.eslocapi.api.dto.PropriedadeRuralMinDtoPost;
import com.jp.eslocapi.api.entities.Persona;
import com.jp.eslocapi.api.entities.PropriedadeRural;
import com.jp.eslocapi.api.exceptions.ProdutorNotFoundException;
import com.jp.eslocapi.api.services.PersonaService;
import com.jp.eslocapi.api.services.PropriedadeRuralRepository;
import com.jp.eslocapi.api.services.PropriedadeRuralService;
import com.jp.eslocapi.core.Gerenciador;

@Service
public class PropriedadeRuralServiceImpl implements PropriedadeRuralService {

	PropriedadeRuralRepository repository;

	PersonaService produtorService;

	public PropriedadeRuralServiceImpl(PropriedadeRuralRepository repository, PersonaService personaService) {

		this.repository = repository;
		this.produtorService = personaService;

	}

	@Override
	public PropriedadeRuralMinDtoPost save(PropriedadeRuralMinDtoPost post) {
		post.setCodigo(gerarCodigoPropriedade(post.getProprietarioCpf()));
		PropriedadeRural propriedade = toPropriedadeRural(post);
		return toPropriedadeRuralMinDtoPost(repository.save(propriedade));
	}

	@Override
	public String gerarCodigoPropriedade(String key) {
		return Gerenciador.GERA_IDENTIFICADOR(key);
		// return "20201005200004459471604";

	}

	@Override
	public PropriedadeRuralMinDtoPost toPropriedadeRuralMinDtoPost(PropriedadeRural saved) {
		return PropriedadeRuralMinDtoPost.builder()
				.codigo(saved.getCodigo())
				.nome(saved.getNome())
				.proprietarioCpf(saved.getProprietario().getNome()).build();
	}

	public boolean exists(PropriedadeRural propriedadde) {
		// Confere com o nome e o proprietario
		return true;
	}

	@Override
	public PropriedadeRural toPropriedadeRural(PropriedadeRuralMinDtoPost post) {
		Persona proprietario = produtorService.getByCpf(post.getProprietarioCpf());

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

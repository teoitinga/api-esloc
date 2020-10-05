package com.jp.eslocapi.api.services.impl;

import org.springframework.stereotype.Service;

import com.jp.eslocapi.api.dto.PropriedadeRuralMinDtoPost;
import com.jp.eslocapi.api.entities.Persona;
import com.jp.eslocapi.api.entities.PropriedadeRural;
import com.jp.eslocapi.api.services.ProdutorService;
import com.jp.eslocapi.api.services.PropriedadeRuralRepository;
import com.jp.eslocapi.api.services.PropriedadeRuralService;

@Service
public class PropriedadeRuralServiceImpl implements PropriedadeRuralService {

	PropriedadeRuralRepository repository;
	
	ProdutorService produtorService;
	
	public PropriedadeRuralServiceImpl(
			PropriedadeRuralRepository repository, 
			ProdutorService personaService
			) {
	
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

		return "20201005200004459471604";//new Gerenciador().GERA_IDENTIFICADOR(key);

	}
	@Override
	public PropriedadeRuralMinDtoPost toPropriedadeRuralMinDtoPost(PropriedadeRural saved) {
		return PropriedadeRuralMinDtoPost.builder()
				.codigo(saved.getCodigo())
				.nome(saved.getNome())
				.proprietarioCpf(saved.getProprietario().getNome())
				.build();
	}
	@Override
	public PropriedadeRural toPropriedadeRural(PropriedadeRuralMinDtoPost post) {
		Persona proprietario = produtorService.getByCpf(post.getProprietarioCpf());
		
		return PropriedadeRural.builder()
				.nome(post.getNome())
				.proprietario(proprietario)
				.build();
	}

}
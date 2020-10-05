package com.jp.eslocapi.api.services.impl;

import org.springframework.stereotype.Service;

import com.jp.eslocapi.api.dto.PropriedadeRuralMinDtoPost;
import com.jp.eslocapi.api.entities.Persona;
import com.jp.eslocapi.api.entities.PropriedadeRural;
import com.jp.eslocapi.api.exceptions.ProdutorNotFoundException;
import com.jp.eslocapi.api.services.PersonaService;
import com.jp.eslocapi.api.services.PropriedadeRuralRepository;
import com.jp.eslocapi.api.services.PropriedadeRuralService;

@Service
public class PropriedadeRuralServiceImpl implements PropriedadeRuralService {

	PropriedadeRuralRepository repository;
	
	PersonaService produtorService;
	
	public PropriedadeRuralServiceImpl(
			PropriedadeRuralRepository repository, 
			PersonaService personaService
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
	public boolean exists(PropriedadeRural propriedadde) {
		//Confere com o nome e o proprietario
		return true;
	}
	@Override
	public PropriedadeRural toPropriedadeRural(PropriedadeRuralMinDtoPost post) {
		Persona proprietario = produtorService.getByCpf(post.getProprietarioCpf()).orElseThrow(()->new ProdutorNotFoundException());
		
		return PropriedadeRural.builder()
				.nome(post.getNome())
				.proprietario(proprietario)
				.build();
	}

}

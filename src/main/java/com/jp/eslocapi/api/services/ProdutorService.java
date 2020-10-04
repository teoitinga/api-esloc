package com.jp.eslocapi.api.services;

import com.jp.eslocapi.api.dto.ProdutorDto;
import com.jp.eslocapi.api.entities.Persona;

public interface ProdutorService {

	Persona save(Persona produtor);

	void delete(Persona toDeleted);

	ProdutorDto update(ProdutorDto dto);

	Persona toProdutor(ProdutorDto dto);

	ProdutorDto toProdutorDto(Persona toSaved);

	Persona getByCpf(String cpfProdutor);

}

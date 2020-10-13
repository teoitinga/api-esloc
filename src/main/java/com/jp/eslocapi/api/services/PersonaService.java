package com.jp.eslocapi.api.services;

import com.jp.eslocapi.api.dto.ProdutorMinDto;
import com.jp.eslocapi.api.entities.Persona;

public interface PersonaService {

	Persona save(ProdutorMinDto productor);

	Persona getByCpf(String proprietarioCpf);

	Boolean existsCpf(String cpf);

	Persona toPersona(ProdutorMinDto productor);

}

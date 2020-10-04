package com.jp.eslocapi.api.services;

import java.util.Optional;

import com.jp.eslocapi.api.dto.ProdutorMinDto;
import com.jp.eslocapi.api.entities.Persona;

public interface PersonaService {

	Persona save(ProdutorMinDto productor);

	Optional<Persona> getByCpf(String proprietarioCpf);

}

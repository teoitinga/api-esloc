package com.jp.eslocapi.api.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jp.eslocapi.api.entities.Persona;
import com.jp.eslocapi.api.entities.PropriedadeRural;

public interface PropriedadeRuralRepository extends JpaRepository<PropriedadeRural, String> {

	PropriedadeRural findByNomeAndProprietario(String nomeDaPropriedade, Persona proprietario);

}

package com.jp.eslocapi.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jp.eslocapi.api.entities.Atendimento;


public interface AtendimentoRepository extends JpaRepository<Atendimento, String>{

	//Optional<Persona> findByCpf(String cpf);

}

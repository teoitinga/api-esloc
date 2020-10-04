package com.jp.eslocapi.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jp.eslocapi.api.entities.Atendimento;
import com.jp.eslocapi.api.entities.Persona;


public interface AtendimentoRepository extends JpaRepository<Atendimento, String>{

	Optional<Persona> findByCpf(String cpf);

}

package com.jp.eslocapi.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jp.eslocapi.api.entities.Persona;

public interface PersonaRepository extends JpaRepository<Persona, String> {

	Optional<Persona> findByCpf(String cpf);

	boolean existsByCpf(String cpf);

	boolean isExistsCpf(String cpf);

}

package com.jp.eslocapi.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jp.eslocapi.api.entities.Tecnico;

public interface TecnicoRespository extends JpaRepository<Tecnico, String>{

	Optional<Tecnico> getByMatricula(String matricula);

}

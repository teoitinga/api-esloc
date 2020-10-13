package com.jp.eslocapi.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jp.eslocapi.api.entities.Servico;

public interface ServicoRepository extends JpaRepository<Servico, String>{

	Optional<Servico> findByCodigo(String codServico);

}

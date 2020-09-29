package com.jp.eslocapi.api.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProgramaMunicipal {
	@Id
	private String codigo;

	private String nomeDoPrograma;

	private String descricao;

	private LocalDate inicioVigencia;
	
	private LocalDate fimVigencia;

	private String parceiros;
	
	@OneToMany
	private List<Persona> pessoasAtendidas;
}

package com.jp.eslocapi.api.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RendaFamiliarAnual {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String codigo;
	
	private LocalDateTime cadastro;
	
	private LocalDateTime atualizacao;
	
	@ManyToOne
	private Tecnico responsavelTecnico;

	@ManyToOne
	private PropriedadeRural unidadeFamiliar;
	
	@ManyToOne
	private Persona segundoTitular;	
	
	@ManyToOne
	private Tecnico emissor;
	
	@OneToMany
	private List<ProducaoFamiliar> producao;
	
	@PrePersist
	private void setCadastro() {
		this.cadastro = LocalDateTime.now();
	}
	
	@PreUpdate
	private void setAtualizacao() {
		this.atualizacao = LocalDateTime.now();
	}
}

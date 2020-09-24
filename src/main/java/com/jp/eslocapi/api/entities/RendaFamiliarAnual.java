package com.jp.eslocapi.api.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "RENDA_FAMILIAR_ANUAL")
public class RendaFamiliarAnual {
	@Id
	@Column(name="codigo")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String codigo;
	
	@Column(name="cadastro")
	private LocalDate cadastro;
	
	@Column(name="atualizacao")
	private LocalDate atualizacao;
	
	@OneToMany
	private Persona responsavelTecnico;

	@OneToMany
	private PropriedadeRural unidadeFamiliar;
	
	@OneToMany
	private Persona emissor;
	
	@OneToMany
	private ProducaoFamiliar producao;
	
	@PrePersist
	private void setCadastro() {
		this.cadastro = LocalDate.now();
	}
	
	@PreUpdate
	private void setAtualizacao() {
		this.atualizacao = LocalDate.now();
	}
}

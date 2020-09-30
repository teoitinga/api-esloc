package com.jp.eslocapi.api.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class Documento {

	@Id
	private String codigo;
	
	private String descricao;
	
	private LocalDateTime cadastro;
	
	private LocalDateTime atualizacao;
	
	@ManyToOne
	private Tecnico emissor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Atendimento atendimento;

	@ManyToOne
	private DocumentoType documento;
	
	@PrePersist
	private void setCadastro() {
		this.cadastro = LocalDateTime.now();
	}
	@PreUpdate
	private void setAtualizacao() {
		this.atualizacao = LocalDateTime.now();
	}
}

package com.jp.eslocapi.api.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ItemAtendimento {
	@Id
	private String codigo;
	
	private int qtd;
	
	private LocalDateTime cadastro;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Tecnico emissor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Persona beneficiario;
	
	@ManyToOne
	private ItemProjeto itemUnitario;
	
	@PrePersist
	private void setCadastro() {
		this.cadastro = LocalDateTime.now();
	}	
}

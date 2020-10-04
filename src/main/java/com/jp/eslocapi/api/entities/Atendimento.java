package com.jp.eslocapi.api.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Atendimento {

	@Id
	private String codigo;
	
	@NotEmpty
	private String recomendacoes;
	
	private LocalDateTime cadastro;
	
	private LocalDateTime atualizacao;
	
	private LocalDate atendimentoData;
	
	@Enumerated(EnumType.STRING)
	private EnumStatus status;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Tecnico responsavelTecnico;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private PropriedadeRural propriedadeRural;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Tecnico emissor;
	
	@Enumerated(EnumType.STRING)
	private EnumConfirm publico;
	
	@OneToMany(mappedBy = "servico", orphanRemoval = true)
	private List<ServicosAtd> servicos;

	@ManyToMany
	private List<Persona> produtores;
	
	@PrePersist
	private void setCadastro() {
		this.cadastro = LocalDateTime.now();
	}
	@PreUpdate
	private void setAtualizacao() {
		this.atualizacao = LocalDateTime.now();
	}
	
}

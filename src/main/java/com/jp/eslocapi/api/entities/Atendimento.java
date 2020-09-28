package com.jp.eslocapi.api.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
	@NotNull(message = "Não é possível fazer um registro sem informar o cpf")
	@NotEmpty(message = "Não é possível fazer um registro sem informar o cpf")
	@NotBlank(message = "Não é possível fazer um registro sem informar o cpf")
	private String codigo;
	
	private String recomendacoes;
	
	private LocalDate cadastro;
	
	private LocalDate atualizacao;
	
	private LocalDate atendimentoData;
	
	@Enumerated(EnumType.STRING)
	private EnumStatus status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Tecnico responsavelTecnico;

	@ManyToOne(fetch = FetchType.LAZY)
	private PropriedadeRural propriedadeRural;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Tecnico emissor;
	
	@Enumerated(EnumType.STRING)
	private EnumConfirm publico;
	
	@OneToMany(mappedBy = "servico")
	private List<ServicosAtd> servicos;

	@OneToMany
	private List<Persona> produtores;
	
	@PrePersist
	private void setCadastro() {
		this.cadastro = LocalDate.now();
	}
	
	@PreUpdate
	private void setAtualizacao() {
		this.atualizacao = LocalDate.now();
	}
	
}

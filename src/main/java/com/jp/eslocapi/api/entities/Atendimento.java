package com.jp.eslocapi.api.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ATENDIMENTO")
public class Atendimento {

	@Id
	@Column(name="codigo")
	@NotNull(message = "Não é possível fazer um registro sem informar o cpf")
	@NotEmpty(message = "Não é possível fazer um registro sem informar o cpf")
	@NotBlank(message = "Não é possível fazer um registro sem informar o cpf")
	private String codigo;
	
	private String recomendacoes;
	
	@Column(name="cadastro")
	private LocalDate cadastro;
	
	@Column(name="atualizacao")
	private LocalDate atualizacao;
	
	@Column(name="data_atendimento")
	private LocalDate atendimentoData;
	
	@Column(name="status_atendimento")
	@Enumerated(EnumType.STRING)
	private EnumStatus status;
	
	@OneToOne
	private Persona responsavelTecnico;
	
	@Column(name="emissor")
	@CPF
	@NotNull(message = "Não é possível fazer um registro sem informar o cpf")
	@NotEmpty(message = "Não é possível fazer um registro sem informar o cpf")
	@NotBlank(message = "Não é possível fazer um registro sem informar o cpf")
	private String emissor;
	
	@Column(name="publicar")
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

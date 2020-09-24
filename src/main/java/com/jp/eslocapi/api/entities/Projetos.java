package com.jp.eslocapi.api.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
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
@Table(name = "PROJETOS")
public class Projetos {
	
	@Id
	@Column(name="codigo")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String codigo;
	
	@Column(name="cadastro")
	private LocalDate cadastro;
	
	@Column(name="atualizacao")
	private LocalDate atualizacao;
	
	@Column(name="data_pagamento")
	private LocalDate dataPagamento;

	@Column(name="valor_orcado")
	@Digits(integer=6, fraction=2)
	private BigDecimal valorOrcado;
	
	@Column(name="valor_financiado")
	@Digits(integer=6, fraction=2)
	private BigDecimal valorFinanciado;
	
	@Column(name="taxa_juros")
	@Digits(integer=6, fraction=2)
	private BigDecimal txJuros;
	
	@Column(name="qtd_parcelas")
	private int qtdParcelas;
	
	@OneToMany
	private Persona responsavelTecnico;

	@OneToMany
	private PropriedadeRural unidadeFamiliar;
	
	@OneToMany
	private Persona emissor;
	
	@PrePersist
	private void setCadastro() {
		this.cadastro = LocalDate.now();
	}
	
	@PreUpdate
	private void setAtualizacao() {
		this.atualizacao = LocalDate.now();
	}
}

package com.jp.eslocapi.api.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Digits;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Projeto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String codigo;
	
	private LocalDate cadastro;
	
	private LocalDate atualizacao;
	
	private LocalDate dataPagamento;

	@Digits(integer=6, fraction=2)
	private BigDecimal valorOrcado;
	
	@Digits(integer=6, fraction=2)
	private BigDecimal valorFinanciado;
	
	@Digits(integer=6, fraction=2)
	private BigDecimal txJuros;

	@ManyToOne
	private ConvTemporal tempoAplicacaoJuros;
	
	private int prazo;

	@ManyToOne
	private ConvTemporal tempoPrazo;
	
	private int qtdParcelas;
	
	@ManyToOne
	private Tecnico responsavelTecnico;

	@ManyToOne
	private PropriedadeRural unidadeFamiliar;
	
	@ManyToOne
	private Tecnico emissor;
	
	@ManyToOne
	private Banco banco;

	@ManyToOne
	private LinhaDeCredito linhaDeCredito;
	
	@PrePersist
	private void setCadastro() {
		this.cadastro = LocalDate.now();
	}
	
	@PreUpdate
	private void setAtualizacao() {
		this.atualizacao = LocalDate.now();
	}
}

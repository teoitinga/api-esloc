package com.jp.eslocapi.api.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
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
@Table(name = "LINHA_DE_CREDITO")
public class LinhaDeCredito {
	@Id
	@Column(name="prefixo_agencia")
	private String prefixoAgencia;
	
	@Column(name="taxa_de_juros_anual")
	@Digits(integer=6, fraction=2)
	private BigDecimal taxaDeJurosAnual;
	
	@Column(name="prazo")
	private int prazo;

	@Column(name="nome_da_linha")
	private int nomeDaLinha;
	
	@Column(name="prazo_maximo")
	private int prazoMaximo;
	
	@Column(name="carenciaMaxima")
	private int carenciaMaxima;
	@Column(name="cadastro")
	private LocalDateTime cadastro;	
	
	@Column(name="atualizacao")
	private LocalDateTime atualizacao;
	
	@PrePersist
	private void setCadastro() {
		this.cadastro = LocalDateTime.now();
	}
	
	@PreUpdate
	private void setAtualizacao() {
		this.atualizacao = LocalDateTime.now();
	}	
}

package com.jp.eslocapi.api.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "BANCOS")
public class Banco {
	@Id
	@Column(name="prefixo_agencia")
	private String prefixoAgencia;
	
	@Column(name="endereco")
	private String endereco;
	
	@Column(name="municipio")
	private String municipio;
	
	@Column(name="nome_gerente")
	private String nomeGerente;
	
	@Column(name="fone_comercial")
	private String foneComercial;
	
	@Column(name="fone_gerente")
	private String foneGerente;
	
	@Column(name="nome_agencia")
	private String nomeAgencia;
	
	@Column(name="email")
	private String email;
	
	@Column(name="cadastro")
	private LocalDate cadastro;	
	
	@Column(name="atualizacao")
	private LocalDate atualizacao;
	
	@PrePersist
	private void setCadastro() {
		this.cadastro = LocalDate.now();
	}
	
	@PreUpdate
	private void setAtualizacao() {
		this.atualizacao = LocalDate.now();
	}
}

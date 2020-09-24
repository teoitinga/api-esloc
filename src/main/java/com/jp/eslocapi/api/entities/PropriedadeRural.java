package com.jp.eslocapi.api.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
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
@Table(name = "PROPRIEDADE_RURAL")
public class PropriedadeRural {
	@Id
	@Column(name="ccir")
	@NotNull(message = "Não é possível fazer um registro sem informar o cpf")
	private String ccir;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name = "area_total")
	@Digits(integer=6, fraction=2)
	private BigDecimal areaTotal;
	
	@Column(name="recibo_car")
	private String reciboCAR;
	
	@Column(name="nirf")
	private String nirf;

	@Column(name="matricula")
	private String matricula;
	
	@Column(name="roteiro_de_acesso")
	private String roteiro;
	
	@Column(name="latitude")
	private String latitude;
	
	@Column(name="longitude")
	private String longitude;
	
	@ManyToOne
	private Persona proprietario;
	
	@ManyToOne
	private Persona emissor;		
	
	@Column(name="cadastro")
	private LocalDate cadastro;
	
	@Column(name="atualizacao")
	private LocalDate atualizacao;	
	
	@Column(name="condicao_posse")
	private EnumPosse condicaoPosse;	
	
	@PrePersist
	private void setCadastro() {
		this.cadastro = LocalDate.now();
	}
	@PreUpdate
	private void setAtualizacao() {
		this.atualizacao = LocalDate.now();
	}
}

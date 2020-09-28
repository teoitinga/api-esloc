package com.jp.eslocapi.api.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class ProducaoFamiliar {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String codigo;
	
	@Digits(integer=6, fraction=2)
	private BigDecimal qtd;

	@Digits(integer=6, fraction=2)
	private BigDecimal valorPorUnidade;
	
	@Digits(integer=6, fraction=2)
	private BigDecimal rendaAuferida;

	@Digits(integer=6, fraction=2)
	private BigDecimal rendaEstimada;
	
	@Enumerated(EnumType.STRING)
	private EnumProgramacComercializacao programa;

	@Enumerated(EnumType.STRING)
	private EnumProgramaSocial programaSocial;
	
	@Enumerated(EnumType.STRING)
	private EnumBeneficioSocial beneficio;

	@Enumerated(EnumType.STRING)
	private EnumOutrasRendas outrasRendas;
	
	@ManyToOne
	private Persona comprador;
	
	
	@ManyToOne
	private Producao producAO;
	
}

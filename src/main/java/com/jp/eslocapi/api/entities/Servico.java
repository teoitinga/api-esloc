package com.jp.eslocapi.api.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
public class Servico {
	@Id
	private String codigo;
	
	private String descricao;
	
	private int tempoEstimado;
	
	@Digits(integer=6, fraction=2)
	private BigDecimal valorEstimado;
	
	private String definicao;
	
	@ManyToOne
	private GrupoServico grupo;
}

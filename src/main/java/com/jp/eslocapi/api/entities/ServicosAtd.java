package com.jp.eslocapi.api.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
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
public class ServicosAtd {
	@Id
	private String codigo;
	
	@Digits(integer=6, fraction=2)
	private BigDecimal valorTotal;

	@Digits(integer=6, fraction=2)
	private BigDecimal valorDae;
	
	private String observacoes;
	
	private String descricao;

	@ManyToOne
	private Atendimento atendimento;
	
	@ManyToOne
	private Servico servico;
}

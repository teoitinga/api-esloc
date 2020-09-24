package com.jp.eslocapi.api.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "PRODUCAO_FAMILIAR")
public class ProducaoFamiliar {
	
	@Id
	@Column(name="codigo")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String codigo;
	
	@Column(name="quantidade_por_unidade")
	@Digits(integer=6, fraction=2)
	private BigDecimal qtd;

	@Column(name="valor_por_unidade")
	@Digits(integer=6, fraction=2)
	private BigDecimal valorPorUnidade;
	
	@Column(name="renda_auferida")
	@Digits(integer=6, fraction=2)
	private BigDecimal rendaAuferida;

	@Column(name="renda_estimada")
	@Digits(integer=6, fraction=2)
	private BigDecimal rendaEstimada;
	
	@OneToMany
	private List<Producao> producoes;
	
}

package com.jp.eslocapi.api.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
*
*Entidade que armazena fatores para tratar as conversões de tempo em 
*dias, meses e anos para auxiliar os cálculos da taxa de juros e pagamentos
*/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CONVERSAO_TEMPORAL")
public class ConvTemporal {
	@Id
	@Column(name="sigla")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String sigla;
	
	private String descricao;
	
	@Column(name="conv_ano")
	@Digits(integer=6, fraction=2)
	private BigDecimal convAno;
	
	@Column(name="conv_mes")
	@Digits(integer=6, fraction=2)
	private BigDecimal convMes;
	
	@Column(name="conv_dia")
	@Digits(integer=6, fraction=2)
	private BigDecimal convDia;
}

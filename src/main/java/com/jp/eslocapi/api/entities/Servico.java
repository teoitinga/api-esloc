package com.jp.eslocapi.api.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@Column(name = "codigo")
	private String codigo;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "tempo_estimado")
	private int tempoEstimado;
	
	@Column(name = "valor_estimado")
	@Digits(integer=6, fraction=2)
	private BigDecimal valorEstimado;
	
	@Column(name = "definicao")
	private String definicao;
	
	@ManyToOne
	private GrupoServico grupo;
}

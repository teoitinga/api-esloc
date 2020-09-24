package com.jp.eslocapi.api.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
@Table(name = "SERVICOS_ATD")
public class ServicosAtd {
	@Id
	@Column(name="codigo")
	private String codigo;
	
	@Column(name="valor_total_servico")
	@Digits(integer=6, fraction=2)
	private BigDecimal valorTotal;

	@Column(name="valor_dae")
	@Digits(integer=6, fraction=2)
	private BigDecimal valorDae;
	
	@Column(name="observacoes")
	private String observacoes;
	
	@Column(name="servico_descricao")
	private String descricao;

	@ManyToOne
	private Atendimento atendimentofk;
	
	@ManyToOne
	private Servico servico;
}

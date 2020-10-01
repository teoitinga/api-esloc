package com.jp.eslocapi.api.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServicosDtoPost {
	
	private String descricao;
	
	private BigDecimal valorDoServico;
	
	private BigDecimal valorCobrado;
	
	private String observacoes;
	
	private String codServico;
	
	private String codAtendimento;
	
}

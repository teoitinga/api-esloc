package com.jp.eslocapi.api.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PropriedadeRuralMinDtoPost {
	private String codigo;

	private String nome;
	
	private String proprietarioCpf;
	
	@Digits(integer=6, fraction=2)
	private BigDecimal areaTotal;
	
}

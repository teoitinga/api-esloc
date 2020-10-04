package com.jp.eslocapi.api.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.Digits;

import org.locationtech.jts.geom.Geometry;

import com.jp.eslocapi.api.entities.EnumLocal;
import com.jp.eslocapi.api.entities.EnumPosse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PropriedadeRuralDto {

	private String codigo;

	private String ccir;
	
	private String nome;
	
	private EnumLocal caracterizacao;
	
	private String localizacao;
	
	@Digits(integer=6, fraction=2)
	private BigDecimal areaTotal;
	
	private String reciboCar;
	
	private String nirf;

	private String matricula;
	
	private String roteiro;
	
	private Geometry perimetro;
	
	private String latitude;
	
	private String longitude;
	
	private String proprietario;

	private List<String> membrosFamiliares;
	
	private String emissor;		
	
	private EnumPosse condicaoPosse;	
}

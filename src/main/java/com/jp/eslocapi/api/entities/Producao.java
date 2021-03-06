package com.jp.eslocapi.api.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Producao {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String codigo;
	
	private String descricao;
	
	private String unidade;
	
	@ManyToOne
	private GrupoProducao grupo;
}

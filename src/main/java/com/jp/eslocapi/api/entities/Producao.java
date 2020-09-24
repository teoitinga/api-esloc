package com.jp.eslocapi.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCAO")
public class Producao {
	@Id
	@Column(name="codigo")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String codigo;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "unidade")
	private String unidade;
	
	@ManyToOne
	private GrupoProducao grupo;
}

package com.jp.eslocapi.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "GRUPO_PRODUCAO")
public class GrupoProducao {
	@Id
	@Column(name="codigo")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String codigo;
	
	@Column(name = "descricao")
	private String descricao;

	@Column(name = "especificacoes")
	private String especificacoes;
}

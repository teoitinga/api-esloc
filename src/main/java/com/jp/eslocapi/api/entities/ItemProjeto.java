package com.jp.eslocapi.api.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ItemProjeto {
	@Id
	private String codigo;
	
	private String nome;
	
	private String descricao;
	
	private String unidade;
}

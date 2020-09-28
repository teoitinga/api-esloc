package com.jp.eslocapi.api.entities;

import javax.persistence.Entity;
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
public class Uregi {
	@Id
	private String cpi;
	
	private String fonefixo;
	
	private String endereco;
	
	private String municipio;
	
	private String email;
	
	@ManyToOne
	private EmpresaAter empresa;

}

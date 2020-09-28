package com.jp.eslocapi.api.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CNPJ;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EmpresaAter {
	@Id
	@NotNull(message = "Não é possível fazer um registro sem informar o cnpj")
	@NotEmpty(message = "Não é possível fazer um registro sem informar o cnpj")
	@NotBlank(message = "Não é possível fazer um registro sem informar o cnpj")
	@CNPJ
	private String cnpj;
	
	private String nomeEmpresa;
	
	private String endereco;
		
}

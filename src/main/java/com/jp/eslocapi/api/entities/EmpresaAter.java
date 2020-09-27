package com.jp.eslocapi.api.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Table(name = "EMPRESA_ATER")
public class EmpresaAter {
	@Id
	@Column(name="cnpj")
	@NotNull(message = "Não é possível fazer um registro sem informar o cnpj")
	@NotEmpty(message = "Não é possível fazer um registro sem informar o cnpj")
	@NotBlank(message = "Não é possível fazer um registro sem informar o cnpj")
	@CNPJ
	private String cnpj;
	
	@Column(name="nome_empresa")
	private String nomeEmpresa;
	
	@Column(name="endereco_empresa")
	private String endereco;
	
	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
	private List<Uregi> uregis;
	
}

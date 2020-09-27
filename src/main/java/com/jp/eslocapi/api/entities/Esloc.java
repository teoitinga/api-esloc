package com.jp.eslocapi.api.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ESLOC")
public class Esloc {
	@Id
	@Column(name="cpi")
	@CPF
	@NotNull(message = "Não é possível fazer um registro sem informar o cpf")
	@NotEmpty(message = "Não é possível fazer um registro sem informar o cpf")
	@NotBlank(message = "Não é possível fazer um registro sem informar o cpf")
	private String cpi;
	
	private String fonefixo;
	
	private String endereco;
	
	private String municipio;
	
	private String email;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Uregi uregi;
	
}

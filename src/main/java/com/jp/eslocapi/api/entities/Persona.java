package com.jp.eslocapi.api.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Email;
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
@Table(name = "PERSONA")
public class Persona {

	@Id
	@CPF
	@NotNull(message = "Não é possível fazer um registro sem informar o cpf")
	@NotEmpty(message = "Não é possível fazer um registro sem informar o cpf")
	@NotBlank(message = "Não é possível fazer um registro sem informar o cpf")
	private String cpf;
	
	private String nome;

	@Email
	private String email;

	private String contato;
	
	private LocalDate nascimento;
	
	private LocalDateTime cadastro;
	
	private LocalDateTime atualizacao;
	
	@Enumerated(EnumType.STRING)
	private EnumCategoria categoria;
	
	private String municipio;
	
	@Enumerated(EnumType.STRING)
	private EnumSexo sexo;
	
	@CPF
	@NotNull(message = "Não é possível fazer um registro sem informar o cpf")
	@NotEmpty(message = "Não é possível fazer um registro sem informar o cpf")
	@NotBlank(message = "Não é possível fazer um registro sem informar o cpf")
	private String emissor;
	
	private EnumEscolaridade escolaridade;
	
	private int indexRonclusao;
	
	private String conselhoRegistro;
	
	@PrePersist
	private void setCadastro() {
		this.cadastro = LocalDateTime.now();
	}
	@PreUpdate
	private void setAtualizacao() {
		this.atualizacao = LocalDateTime.now();
	}
}

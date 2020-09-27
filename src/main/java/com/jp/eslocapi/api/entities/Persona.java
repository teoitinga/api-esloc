package com.jp.eslocapi.api.entities;

import java.time.LocalDate;

import javax.persistence.Column;
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
	@Column(name="cpf")
	@CPF
	@NotNull(message = "Não é possível fazer um registro sem informar o cpf")
	@NotEmpty(message = "Não é possível fazer um registro sem informar o cpf")
	@NotBlank(message = "Não é possível fazer um registro sem informar o cpf")
	private String cpf;
	
	@Column(name="nome")
	private String nome;

	@Column(name="email")
	@Email
	private String email;

	@Column(name="contato")
	private String contato;
	
	@Column(name="nascimento")
	private LocalDate nascimento;
	
	@Column(name="cadastro")
	private LocalDate cadastro;
	
	@Column(name="atualizacao")
	private LocalDate atualizacao;
	
	@Column(name="categoria")
	@Enumerated(EnumType.STRING)
	private EnumCategoria categoria;
	
	@Column(name="municipio")
	private String municipio;
	
	@Column(name="sexo")
	@Enumerated(EnumType.STRING)
	private EnumSexo sexo;
	
	@Column(name="emissor")
	@CPF
	@NotNull(message = "Não é possível fazer um registro sem informar o cpf")
	@NotEmpty(message = "Não é possível fazer um registro sem informar o cpf")
	@NotBlank(message = "Não é possível fazer um registro sem informar o cpf")
	private String emissor;
	
	@Column(name="escolaridade")
	private EnumEscolaridade escolaridade;
	
	@Column(name="index_conclusao")
	private int indexRonclusao;
	
	@Column(name="conselho_registro")
	private String conselhoRegistro;
	
	@PrePersist
	private void setCadastro() {
		this.cadastro = LocalDate.now();
	}
	@PreUpdate
	private void setAtualizacao() {
		this.atualizacao = LocalDate.now();
	}
}

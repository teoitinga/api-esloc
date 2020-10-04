package com.jp.eslocapi.api.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

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
public class Persona {

	@Id
	@CPF
	@NotEmpty(message = "Não é possível fazer um registro sem informar o cpf")
	private String cpf;
	
	@NotEmpty
	private String nome;

	@Email
	private String email;

	private String contato;
	
	private String enderecoResidencial;
	
	private LocalDate nascimento;
	
	private String cpfEmissor;
	
	private LocalDateTime cadastro;
	
	private LocalDateTime atualizacao;
	
	@Enumerated(EnumType.STRING)
	private EnumCategoria categoria;
	
	private String municipio;
	
	@Enumerated(EnumType.STRING)
	private EnumSexo sexo;
	
	private EnumEscolaridade escolaridade;
	
	private int indexConclusao;
	
	@PrePersist
	private void setCadastro() {
		this.cadastro = LocalDateTime.now();
	}
	@PreUpdate
	private void setAtualizacao() {
		this.atualizacao = LocalDateTime.now();
	}
}

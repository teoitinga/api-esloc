package com.jp.eslocapi.api.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TECNICOS")
public class Tecnico {
	@Id
	@Column(name="matricula")
	@NotNull(message = "Não é possível fazer um registro sem informar o matricula")
	@NotEmpty(message = "Não é possível fazer um registro sem informar o matricula")
	@NotBlank(message = "Não é possível fazer um registro sem informar o matricula")
	private String matricula;

	private String registro;
	
	private String conselho;
	
	@OneToOne
	private Persona agente;
	
	@Column(name="password")
	private String password;
	
	@Column(name="permissao")
	@Enumerated(EnumType.STRING)
	private EnumPermissao permissao;
	
	@ManyToOne
	private Esloc esloc;
}

package com.jp.eslocapi.api.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.Digits;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BoiPrice {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String codigo;
	
	@ManyToOne
	private Tecnico emissor;
	
	@ManyToOne
	private PropriedadeRural propriedadeInfo;
	
	@Digits(integer=6, fraction=2)
	private BigDecimal valor;
	
	@Enumerated(EnumType.STRING)
	private EnumCategoriaAnimal categoriaAnimal;
	

	private LocalDateTime cadastro;
	
	@PrePersist
	private void setCadastro() {
		this.cadastro = LocalDateTime.now();
	}
}

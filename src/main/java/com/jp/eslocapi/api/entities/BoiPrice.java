package com.jp.eslocapi.api.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
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
@Table(name = "MILK_PRICE")
public class BoiPrice {
	@Id
	@Column(name="codigo")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String codigo;
	
	@ManyToOne
	private Persona emissor;
	
	@ManyToOne
	private Persona produtorInfo;
	
	@Column(name = "valor_por_unidade")
	@Digits(integer=6, fraction=2)
	private BigDecimal valor;
	
	@Column(name="categoria_Animal")
	@Enumerated(EnumType.STRING)
	private EnumCategoriaanimal categoriaAnimal;
	
	@Column(name="cadastro")
	private LocalDate cadastro;
	
	@PrePersist
	private void setCadastro() {
		this.cadastro = LocalDate.now();
	}
}

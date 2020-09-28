package com.jp.eslocapi.api.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Digits;

import org.locationtech.jts.geom.Geometry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PropriedadeRural {
	@Id
	private String codigo;

	private String ccir;
	
	private String nome;
	
	private EnumLocal caracterizacao;
	
	private String localizacao;
	
	@Digits(integer=6, fraction=2)
	private BigDecimal areaTotal;
	
	private String reciboCar;
	
	private String nirf;

	private String matricula;
	
	private String roteiro;
	
	private Geometry perimetro;
	
	private String latitude;
	
	private String longitude;
	
	@ManyToOne
	private Persona proprietario;

	@OneToMany
	private List<Persona> membrosFamiliares;
	
	@ManyToOne
	private Tecnico emissor;		
	
	private LocalDateTime cadastro;
	
	private LocalDateTime atualizacao;	
	
	private EnumPosse condicaoPosse;	
	
	@PrePersist
	private void setCadastro() {
		this.cadastro = LocalDateTime.now();
	}
	@PreUpdate
	private void setAtualizacao() {
		this.atualizacao = LocalDateTime.now();
	}
}

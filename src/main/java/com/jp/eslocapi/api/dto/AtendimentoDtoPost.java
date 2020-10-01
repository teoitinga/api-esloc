package com.jp.eslocapi.api.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AtendimentoDtoPost {

	private String codigo;
	private String recomendacoes;
	private String dataAtd;
	private String tecnico;
	private String local;
	private List<ServicosDtoPost> servicos;
	private List<ProdutorMinDto> produtores;
	private String statusAtd;
	private String emissor;

}

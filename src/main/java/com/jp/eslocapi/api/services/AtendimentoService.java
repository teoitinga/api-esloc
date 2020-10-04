package com.jp.eslocapi.api.services;

import java.util.List;

import com.jp.eslocapi.api.dto.AtendimentoDtoPost;
import com.jp.eslocapi.api.dto.ProdutorMinDto;
import com.jp.eslocapi.api.entities.Atendimento;

public interface AtendimentoService {

	AtendimentoDtoPost save(AtendimentoDtoPost any);

	Atendimento toAtendimento(AtendimentoDtoPost post);

	List<ProdutorMinDto> getInvalidCpfs(List<ProdutorMinDto> produtores);

	boolean cpfValid(String cpf);

	List<ProdutorMinDto> getValidCpfs(List<ProdutorMinDto> produtores);

	List<ProdutorMinDto> getNotExistentProductors(List<ProdutorMinDto> produtors);

	List<ProdutorMinDto> getExistentProductors(List<ProdutorMinDto> produtors);

	boolean isRegistered(String cpf);

}

package com.jp.eslocapi.api.services;

import com.jp.eslocapi.api.entities.Servico;

public interface ServicoService {

	Servico findByCodServico(String codServico);

	Servico save(Servico createValidServico);

}

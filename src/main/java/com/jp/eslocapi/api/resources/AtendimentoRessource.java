package com.jp.eslocapi.api.resources;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jp.eslocapi.api.dto.AtendimentoDtoPost;
import com.jp.eslocapi.api.services.AtendimentoService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/atendimentos")
public class AtendimentoRessource {

	private AtendimentoService service;
	
	
	public AtendimentoRessource(AtendimentoService service) {
		this.service = service;
	}


	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AtendimentoDtoPost createAtendimentoTest(@RequestBody AtendimentoDtoPost dto) {
		return this.service.save(dto);
	}
}

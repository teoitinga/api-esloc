package com.jp.eslocapi.api.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jp.eslocapi.api.dto.AtendimentoDtoPost;
import com.jp.eslocapi.api.dto.AtendimentoInfoDto;
import com.jp.eslocapi.api.exceptions.ApiErrors;
import com.jp.eslocapi.api.services.AtendimentoService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/atendimentos")
@Slf4j
public class AtendimentoRessource {

	private AtendimentoService service;

	public AtendimentoRessource(AtendimentoService service) {
		this.service = service;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AtendimentoDtoPost createAtendimentoTest(@RequestBody @Valid AtendimentoDtoPost dto) {
		return this.service.save(dto);
	}
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<AtendimentoInfoDto> obtemAtendimento() {
		return this.service.getAtendimentos();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleValidationException(MethodArgumentNotValidException exception) {
		BindingResult bindingResult = exception.getBindingResult();
		return new ApiErrors(bindingResult);
	}
}

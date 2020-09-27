package com.jp.eslocapi.api.resources;

import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jp.eslocapi.api.repositories.AtendimentoRepository;
import com.jp.eslocapi.api.services.AtendimentoService;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class AtendimentoRessourceTest {
	static String PRODUTOR_API = "/api/v1/produtores";
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@MockBean
	AtendimentoService service;

	@Autowired
	private AtendimentoRepository repository;
	
	@Test
	@DisplayName("Deve registrar um atendimento com sucesso")
	public void createAtendimentoTest() throws Exception {
		
	}
}

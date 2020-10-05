package com.jp.eslocapi.core;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jp.eslocapi.api.configs.Configuration;

@Component
public class Gerenciador {
	
	@Autowired
	private static Configuration folderDate;
	
	public static String GERA_IDENTIFICADOR(String key) {
		
		LocalDateTime dataDoAtendimentoTime = LocalDateTime.now(); 
		
		StringBuilder codigo = new StringBuilder();
		
		codigo.append(dataDoAtendimentoTime.format(folderDate.keyDateTimeFormater()));
		codigo.append(key);
		
		return codigo.toString();
	}
}

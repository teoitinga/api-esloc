package com.jp.eslocapi.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Gerenciador {
	
	public static String GERA_IDENTIFICADOR(String key) {
		
		LocalDateTime dataDoAtendimentoTime = LocalDateTime.now(); 
		
		StringBuilder codigo = new StringBuilder();
		
		codigo.append(dataDoAtendimentoTime.format(DateTimeFormatter.ofPattern("yyyyMMddhhmm")));
		codigo.append(key);
		
		return codigo.toString();
	}
}

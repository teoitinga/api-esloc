package com.jp.eslocapi.api.exceptions;

public class TecnicoNotFoundException  extends RuntimeException {
	

	private static final long serialVersionUID = -6878718352567743727L;

	public TecnicoNotFoundException() {
		super("Tecnico não registrado no banco de dados.");
	}
}

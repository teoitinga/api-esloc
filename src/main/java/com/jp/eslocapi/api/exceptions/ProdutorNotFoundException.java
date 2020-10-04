package com.jp.eslocapi.api.exceptions;

public class ProdutorNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 342296956066915407L;

	public ProdutorNotFoundException() {
		super("Produtor não registrado no banco de dados.");
	}

}

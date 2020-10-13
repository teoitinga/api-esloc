package com.jp.eslocapi.api.exceptions;

public class NotListProductorsException extends RuntimeException{

	private static final long serialVersionUID = -516983486334095348L;

	public NotListProductorsException() {
		super("Não há produtores informados!");

	}
}

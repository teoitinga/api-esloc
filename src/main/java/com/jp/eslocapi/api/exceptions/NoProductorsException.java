package com.jp.eslocapi.api.exceptions;

public class NoProductorsException extends RuntimeException{
	private static final long serialVersionUID = -1955601627116721053L;

	public NoProductorsException() {
		super("Não há produtores informados!");

	}

	
}

package com.jp.eslocapi.api.exceptions;

public class ServiceNotFoundException  extends RuntimeException {

	private static final long serialVersionUID = 6400809311315544204L;

	public ServiceNotFoundException() {
		super("Serviço não registrado no banco de dados.");
	}
}

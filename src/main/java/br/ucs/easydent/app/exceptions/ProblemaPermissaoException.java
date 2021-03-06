package br.ucs.easydent.app.exceptions;

import javax.ws.rs.core.Response.Status;

public class ProblemaPermissaoException extends EasydentException {

	private static final long serialVersionUID = 1L;
	private String mensagem;

	public ProblemaPermissaoException() {
		super("Você não possui permissão para acessar esse recurso/método.");
	}

	public ProblemaPermissaoException(String mensagem) {
		super(mensagem);
		this.mensagem = mensagem;
	}

	public Status getStatus() {
		return Status.FORBIDDEN;
	}

	@Override
	public String getMessage() {
		return mensagem;
	}

}

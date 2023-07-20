package com.gabriel.msavaliadorcredito.ex;

public class DadosClienteNotFoundException  extends Exception{
	private static final long serialVersionUID = 1L;

	public DadosClienteNotFoundException() {
		super("dados do cliente nao encontrados para cpf informado");
	}
	
}

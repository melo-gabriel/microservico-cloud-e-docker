package com.gabriel.msclientes.DTO;

import com.gabriel.msclientes.model.Clientes;

import lombok.Data;

@Data
public class ClientesDTO {

	private String cpf;
	private String nome;
	private Integer idade;
	
	public Clientes toModel() {
		return new Clientes(cpf, nome, idade);
	}
}

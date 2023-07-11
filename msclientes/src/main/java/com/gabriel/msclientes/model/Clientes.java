package com.gabriel.msclientes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "TB_CLIENTE")
public class Clientes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cpf;
	private String nome;
	private Integer idade;
	
	public Clientes(String cpf, String nome, Integer idade) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.idade = idade;
	}

	
}

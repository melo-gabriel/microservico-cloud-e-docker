package com.gabriel.msavaliadorcredito.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosAvaliacao {
	
	private String cpf;
	private Long renda;

}

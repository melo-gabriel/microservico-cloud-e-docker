package com.gabriel.msavaliadorcredito.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cartoes {
	
	private Long id;
	private String nome;
	private String bandeira;
	private BigDecimal limiteBasico;

}

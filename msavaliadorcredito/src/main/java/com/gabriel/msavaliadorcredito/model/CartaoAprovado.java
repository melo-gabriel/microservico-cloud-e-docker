package com.gabriel.msavaliadorcredito.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartaoAprovado {
	
	private String cartao;
	private String bandeira;
	private BigDecimal limiteAprovado; 

}

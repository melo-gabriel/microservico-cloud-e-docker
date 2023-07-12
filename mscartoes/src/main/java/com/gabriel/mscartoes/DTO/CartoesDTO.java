package com.gabriel.mscartoes.DTO;

import java.math.BigDecimal;

import com.gabriel.mscartoes.model.BandeiraCartao;
import com.gabriel.mscartoes.model.Cartoes;

import lombok.Data;

@Data
public class CartoesDTO {

	private String nome;
	private BandeiraCartao bandeira;
	private BigDecimal renda;
	private BigDecimal limite;

	public Cartoes toModel() {
		return new Cartoes(nome, bandeira, renda, limite);
	}

}

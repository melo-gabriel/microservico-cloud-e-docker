package com.gabriel.mscartoes.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabriel.mscartoes.model.Cartoes;
import com.gabriel.mscartoes.repositories.CartoesRepository;

@Service
public class CartoesService {

	@Autowired
	private CartoesRepository cartoesRepository;

	@Transactional
	public Cartoes save(Cartoes cartoes) {
		return cartoesRepository.save(cartoes);
	}

	public List<Cartoes> getCartoesRendaMenorIgual(Long renda) {
		var rendaBigDecimal = BigDecimal.valueOf(renda);
		return cartoesRepository.findByRendaLessThanEqual(rendaBigDecimal);
	}

}

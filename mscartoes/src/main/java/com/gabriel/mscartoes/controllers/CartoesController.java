package com.gabriel.mscartoes.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.mscartoes.DTO.CartoesDTO;
import com.gabriel.mscartoes.model.Cartoes;
import com.gabriel.mscartoes.services.CartoesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cartao")
@RequiredArgsConstructor
public class CartoesController {

	private final CartoesService cartoesService;

	@GetMapping
	public String status() {
		return "ok";
	}

	@PostMapping("/salvar")
	public ResponseEntity<?> cadastrar(@RequestBody CartoesDTO cartoesDTO) {
		var cartoes = cartoesDTO.toModel();
		cartoesService.save(cartoes);
		return ResponseEntity.status(HttpStatus.CREATED).body(cartoes);

	}

	@GetMapping("/renda:{renda}")
	public ResponseEntity<List<Cartoes>> getCartoesRendaAte(@PathVariable Long renda) {
		List<Cartoes> list = cartoesService.getCartoesRendaMenorIgual(renda);
		return ResponseEntity.ok(list);
	}

}

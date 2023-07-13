package com.gabriel.msavaliadorcredito.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.msavaliadorcredito.model.SituacaoCliente;
import com.gabriel.msavaliadorcredito.services.AvaliadorCreditoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/avaliacoes-credito")
@RequiredArgsConstructor
public class AvaliadorCreditoController {

	private final AvaliadorCreditoService avaliadorCreditoServer;

	@GetMapping
	public String status() {
		return "ok";
	}

	@GetMapping("/situacao-cliente:{cpf}")
	public ResponseEntity<SituacaoCliente> consultaSituacaoCliente(@PathVariable String cpf){
		var situacaoCliente = avaliadorCreditoServer.obterSituacaoCliente(cpf);
		return ResponseEntity.ok(situacaoCliente);
	}
}

package com.gabriel.msavaliadorcredito.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.msavaliadorcredito.ex.DadosClienteNotFoundException;
import com.gabriel.msavaliadorcredito.ex.ErroComunicacaoMicrosservicesException;
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
	public ResponseEntity<?> consultaSituacaoCliente(@PathVariable String cpf) {
		try {
			var situacaoCliente = avaliadorCreditoServer.obterSituacaoCliente(cpf);
			return ResponseEntity.ok(situacaoCliente);
		} catch (DadosClienteNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (ErroComunicacaoMicrosservicesException e) {
			return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
		}

	}
}

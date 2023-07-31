package com.gabriel.msavaliadorcredito.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.msavaliadorcredito.ex.DadosClienteNotFoundException;
import com.gabriel.msavaliadorcredito.ex.ErroComunicacaoMicrosservicesException;
import com.gabriel.msavaliadorcredito.ex.ErroSolicitacaoCartaoException;
import com.gabriel.msavaliadorcredito.model.DadosAvaliacao;
import com.gabriel.msavaliadorcredito.model.DadosSolicitacaoEmissaoCartao;
import com.gabriel.msavaliadorcredito.model.ProtocoloSolicitacaoCartao;
import com.gabriel.msavaliadorcredito.model.RetornoAvaliacaoCliente;
import com.gabriel.msavaliadorcredito.services.AvaliadorCreditoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/avaliacoes-credito")
@RequiredArgsConstructor
public class AvaliadorCreditoController {

	private final AvaliadorCreditoService avaliadorCreditoService;

	@GetMapping
	public String status() {
		return "ok";
	}

	@GetMapping("/situacao-cliente:{cpf}")
	public ResponseEntity<?> consultarSituacaoCliente(@PathVariable String cpf) {
		try {
			var situacaoCliente = avaliadorCreditoService.obterSituacaoCliente(cpf);
			return ResponseEntity.ok(situacaoCliente);
		} catch (DadosClienteNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (ErroComunicacaoMicrosservicesException e) {
			return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<?> realizarAvaliacao(@RequestBody DadosAvaliacao dados) {
		try {
			RetornoAvaliacaoCliente retornoAvaliacaoCliente = avaliadorCreditoService.realizarAvaliacao(dados.getCpf(),
					dados.getRenda());
			return ResponseEntity.ok(retornoAvaliacaoCliente);
		} catch (DadosClienteNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (ErroComunicacaoMicrosservicesException e) {
			return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
		}
	}

	@PostMapping("/solicitacoes-cartao")
	public ResponseEntity<?> solicitarCartoes(@RequestBody DadosSolicitacaoEmissaoCartao dados) {
		try {
			ProtocoloSolicitacaoCartao protocoloSolicitacaoCartao = avaliadorCreditoService
					.solicitarEmissaoCartao(dados);
			return ResponseEntity.ok(protocoloSolicitacaoCartao);

		} catch (ErroSolicitacaoCartaoException e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
}

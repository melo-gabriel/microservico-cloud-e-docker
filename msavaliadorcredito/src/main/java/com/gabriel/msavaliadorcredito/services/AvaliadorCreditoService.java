package com.gabriel.msavaliadorcredito.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gabriel.msavaliadorcredito.infra.clients.ClienteResourceClient;
import com.gabriel.msavaliadorcredito.model.DadosCliente;
import com.gabriel.msavaliadorcredito.model.SituacaoCliente;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {
	
	private final ClienteResourceClient clienteResourceClient;

	public SituacaoCliente obterSituacaoCliente(String cpf) {
	// obterDadosCliente  - MSCLIENTES
	// obterCartoesCliente - MSCARTOES	
		
	ResponseEntity<DadosCliente> dadosClienteResponse = clienteResourceClient.dadosCliente(cpf);
	return SituacaoCliente
			.builder()
			.cliente(dadosClienteResponse.getBody())
			.build();

	}

}

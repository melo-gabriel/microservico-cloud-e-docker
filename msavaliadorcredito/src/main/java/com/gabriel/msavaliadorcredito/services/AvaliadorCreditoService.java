package com.gabriel.msavaliadorcredito.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gabriel.msavaliadorcredito.ex.DadosClienteNotFoundException;
import com.gabriel.msavaliadorcredito.ex.ErroComunicacaoMicrosservicesException;
import com.gabriel.msavaliadorcredito.infra.clients.CartoesResourceClient;
import com.gabriel.msavaliadorcredito.infra.clients.ClienteResourceClient;
import com.gabriel.msavaliadorcredito.model.CartaoCliente;
import com.gabriel.msavaliadorcredito.model.DadosCliente;
import com.gabriel.msavaliadorcredito.model.SituacaoCliente;

import feign.FeignException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

	private final ClienteResourceClient clienteResourceClient;
	private final CartoesResourceClient cartoesResourceClient;

	public SituacaoCliente obterSituacaoCliente(String cpf)
			throws DadosClienteNotFoundException, ErroComunicacaoMicrosservicesException {
		// obterDadosCliente - MSCLIENTES
		// obterCartoesCliente - MSCARTOES

		try {
			ResponseEntity<DadosCliente> dadosClienteResponse = clienteResourceClient.dadosCliente(cpf);
			ResponseEntity<List<CartaoCliente>> cartoesResponse = cartoesResourceClient.getCartoesByCliente(cpf);

			return SituacaoCliente.builder().cliente(dadosClienteResponse.getBody()).cartoes(cartoesResponse.getBody())
					.build();

		} catch (FeignException.FeignClientException e) {
			int status = e.status();
			if (HttpStatus.NOT_FOUND.value() == status) {
				throw new DadosClienteNotFoundException();
			}
			throw new ErroComunicacaoMicrosservicesException(e.getMessage(), status);
			
		}
	}
}

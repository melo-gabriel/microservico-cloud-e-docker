package com.gabriel.msavaliadorcredito.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gabriel.msavaliadorcredito.ex.DadosClienteNotFoundException;
import com.gabriel.msavaliadorcredito.ex.ErroComunicacaoMicrosservicesException;
import com.gabriel.msavaliadorcredito.infra.clients.CartoesResourceClient;
import com.gabriel.msavaliadorcredito.infra.clients.ClienteResourceClient;
import com.gabriel.msavaliadorcredito.model.CartaoAprovado;
import com.gabriel.msavaliadorcredito.model.CartaoCliente;
import com.gabriel.msavaliadorcredito.model.Cartoes;
import com.gabriel.msavaliadorcredito.model.DadosCliente;
import com.gabriel.msavaliadorcredito.model.RetornoAvaliacaoCliente;
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

    public RetornoAvaliacaoCliente realizarAvaliacao(String cpf, Long renda)
            throws DadosClienteNotFoundException, ErroComunicacaoMicrosservicesException{
        try{
            ResponseEntity<DadosCliente> dadosClienteResponse = clienteResourceClient.dadosCliente(cpf);
            ResponseEntity<List<Cartoes>> cartoesResponse = cartoesResourceClient.getCartoesRendaAte(renda);

            List<Cartoes> cartoes = cartoesResponse.getBody();
            var listaCartoesAprovados = cartoes.stream().map(cartao -> {

                DadosCliente dadosCliente = dadosClienteResponse.getBody();

                BigDecimal limiteBasico = cartao.getLimiteBasico();
                BigDecimal idadeBD = BigDecimal.valueOf(dadosCliente.getIdade());
                var fator = idadeBD.divide(BigDecimal.valueOf(10));
                BigDecimal limiteAprovado = fator.multiply(limiteBasico);

                CartaoAprovado aprovado = new CartaoAprovado();
                aprovado.setCartao(cartao.getNome());
                aprovado.setBandeira(cartao.getBandeira());
                aprovado.setLimiteAprovado(limiteAprovado);

                return aprovado;
            }).collect(Collectors.toList());

            return new RetornoAvaliacaoCliente(listaCartoesAprovados);
		} catch (FeignException.FeignClientException e) {
			int status = e.status();
			if (HttpStatus.NOT_FOUND.value() == status) {
				throw new DadosClienteNotFoundException();
			}
			throw new ErroComunicacaoMicrosservicesException(e.getMessage(), status);
		}
	}
}

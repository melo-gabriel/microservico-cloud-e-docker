package com.gabriel.mscartoes.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gabriel.mscartoes.model.ClienteCartao;
import com.gabriel.mscartoes.repositories.ClienteCartaoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {

	private final ClienteCartaoRepository clienteCartaoRepository;

	public List<ClienteCartao> ListCataoByCpf(String cpf) {
		return clienteCartaoRepository.findByCpf(cpf);
	}
}

package com.gabriel.msclientes.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabriel.msclientes.model.Clientes;
import com.gabriel.msclientes.repositories.ClientesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientesService {
	
	private final ClientesRepository clientesRepository;
	
	@Transactional
	public Clientes save(Clientes clientes) {
		return clientesRepository.save(clientes);
	}
	
	public Optional<Clientes> getByCpf(String cpf){
		return clientesRepository.findByCpf(cpf);
		
	}
}

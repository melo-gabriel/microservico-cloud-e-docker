package com.gabriel.msavaliadorcredito.infra.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gabriel.msavaliadorcredito.model.DadosCliente;

@FeignClient(value = "msclientes", path = "/clientes")
public interface ClienteResourceClient {

	@GetMapping("/{cpf}")
	ResponseEntity<DadosCliente> dadosCliente(@PathVariable String cpf);
		
}
package com.gabriel.msavaliadorcredito.infra.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gabriel.msavaliadorcredito.model.CartaoCliente;

@FeignClient(value = "mscartoes", path = "/cartoes")
public interface CartoesResourceClient {

	@GetMapping("/cpf:{cpf}")
	ResponseEntity<List<CartaoCliente>> getCartoesByCliente(@PathVariable String cpf);
}
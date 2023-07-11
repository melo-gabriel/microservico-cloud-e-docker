package com.gabriel.msclientes.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabriel.msclientes.DTO.ClientesDTO;
import com.gabriel.msclientes.services.ClientesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class clientesController {
	
	private final ClientesService clientesService;
	
	@GetMapping
	public String status(){
		return "ok";
	}
		
	@PostMapping
	public ResponseEntity<?> save(@RequestBody ClientesDTO clientesDTO) {
	var cliente = clientesDTO.toModel();
	clientesService.save(cliente);
	URI headerLocation = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.query("cpf={cpf}")
			.buildAndExpand(cliente.getCpf())
			.toUri();
	return ResponseEntity.created(headerLocation).build();
	}

	@GetMapping("/{cpf}")
	public ResponseEntity<?> dadosCliente(@PathVariable String cpf) {
		var cliente = clientesService.getByCpf(cpf);
		if(cliente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cliente);
		
	}
	
	
}

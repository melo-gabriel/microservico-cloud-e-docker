package com.gabriel.mscartoes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.mscartoes.model.ClienteCartao;

public interface ClienteCartaoRepository extends  JpaRepository<ClienteCartao, Long> {
	 List<ClienteCartao> findByCpf(String cpf);

}

package com.gabriel.mscartoes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabriel.mscartoes.model.ClienteCartao;

@Repository
public interface ClienteCartaoRepository extends  JpaRepository<ClienteCartao, Long> {
	 List<ClienteCartao> findByCpf(String cpf);

}

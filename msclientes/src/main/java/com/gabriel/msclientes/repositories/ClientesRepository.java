package com.gabriel.msclientes.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabriel.msclientes.model.Clientes;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Long> {

	Optional<Clientes> findByCpf(String cpf);

}

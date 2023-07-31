package com.gabriel.mscartoes.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabriel.mscartoes.model.Cartoes;

@Repository
public interface CartoesRepository extends JpaRepository<Cartoes, Long>{

	List<Cartoes> findByRendaLessThanEqual(BigDecimal rendaBigDecimal);

}

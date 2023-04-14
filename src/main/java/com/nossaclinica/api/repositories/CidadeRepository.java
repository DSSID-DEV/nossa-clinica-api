package com.nossaclinica.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nossaclinica.api.models.entities.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{

}

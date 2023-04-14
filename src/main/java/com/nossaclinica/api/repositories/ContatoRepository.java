package com.nossaclinica.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nossaclinica.api.models.entities.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long>{

}

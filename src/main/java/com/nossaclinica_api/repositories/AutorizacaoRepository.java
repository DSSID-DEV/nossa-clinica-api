package com.nossaclinica_api.repositories;

import com.nossaclinica_api.models.entities.Autorizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorizacaoRepository extends JpaRepository<Autorizacao, Long> {
}

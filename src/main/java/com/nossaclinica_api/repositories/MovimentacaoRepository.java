package com.nossaclinica_api.repositories;


import com.nossaclinica_api.models.entities.Movimentacao;
import com.nossaclinica_api.repositories.queries.MovimentacaoQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

}

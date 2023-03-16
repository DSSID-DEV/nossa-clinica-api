package com.nossaclinica.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nossaclinica.api.models.entities.Especializacao;

@Repository
public interface EspecializacaoRepository extends JpaRepository<Especializacao, Long>{

	List<Especializacao> findAllByDescricaoContainingIgnoreCase(String descricao);

//	@Query(EspecializacaoQueries.BUSCAR_POR_DESCRICAO)
//	List<Especializacao> buscarPorDescricao(String descricao);

}

package com.nossaclinica.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nossaclinica.api.models.entities.MedicoEspecialista;
import com.nossaclinica.api.repositories.queries.MedicoEspecialistasQueries;

@Repository
public interface MedicoEspecialistaRepository extends JpaRepository<MedicoEspecialista, Long>{

	List<MedicoEspecialista> findAllByNomeContainingIgnoreCase(String nome);

	@Query(value = MedicoEspecialistasQueries.LISTAR_POR_ESPECIALIDADE)
	List<MedicoEspecialista> listarPorEspecialidade(Long idEspecialidade);

}

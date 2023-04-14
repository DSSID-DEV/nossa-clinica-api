package com.nossaclinica.api.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nossaclinica.api.models.entities.Usuario;
import com.nossaclinica.api.repositories.queries.UsuarioQueries;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	@Query(UsuarioQueries.BUSCAR_POR_USERNAME_AND_NASCIDO_EM)
	Usuario buscarPorUserNameEDataDeNascimento(String username, LocalDate nascidoEm);

}

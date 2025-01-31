package com.nossaclinica_api.repositories;

import com.nossaclinica_api.models.entities.Especialista;
import com.nossaclinica_api.repositories.queries.EspecialistaQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EspecialistaRepository extends JpaRepository<Especialista, Long> {

    @Query(value = EspecialistaQueries.VERIFICAR_SE_EXISTE_MEDICO_CADASTRADO, nativeQuery = false)
    boolean existsEspecialista(@Param("nome") String nome, @Param("telefone") String telefone,
                               @Param("nascidoEm") LocalDate nascidoEm, @Param("email") String email);

    @Query(value = EspecialistaQueries.RELACIONAR_MEDICOS_ATIVOS, nativeQuery = false)
    List<Especialista> findAllAtivos();

}

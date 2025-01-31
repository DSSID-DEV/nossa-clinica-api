package com.nossaclinica_api.repositories;


import com.nossaclinica_api.models.entities.FormaDePagamento;
import com.nossaclinica_api.repositories.queries.FormaDePagamentoQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormaDePagamentoRespository extends JpaRepository<FormaDePagamento, Long> {


    @Query(value = FormaDePagamentoQueries.LISTAR_POR_ID, nativeQuery = false)
    List<FormaDePagamento> findInById(@Param("ids") List<Long> ids);
}

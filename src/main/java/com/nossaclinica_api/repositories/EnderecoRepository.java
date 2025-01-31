package com.nossaclinica_api.repositories;

import com.nossaclinica_api.models.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query(value = EnderecoQueries.BUSCAR_ENDERECO_POR_FILTROS)
    Endereco buscarEnderecoPor(@Param("cidade") String cidade, @Param("bairro") String bairro,
                               @Param("tipoLogradouro") String tipoLogradouro,
                               @Param("logradouro") String logradouro, @Param("numero") String numero);
}

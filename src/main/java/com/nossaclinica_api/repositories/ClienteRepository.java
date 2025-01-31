package com.nossaclinica_api.repositories;

import com.nossaclinica_api.models.entities.Cliente;
import com.nossaclinica_api.repositories.queries.ClienteQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query(value = ClienteQueries.VERIFICAR_SE_EXISTE_CLIENTE_CADASTRADO, nativeQuery = false)
    boolean existsCliente(@Param("nome") String nome, @Param("telefone") String telefone,
                          @Param("nascidoEm") LocalDate nascidoEm, @Param("email") String email);

    @Query(value = ClienteQueries.VERIFICAR_SE_EXISTE_CLIENTE_DO_ID_CLIENTE, nativeQuery = false)
    boolean existsClienteComIdCliente(@Param("idCliente") Long idCliente);

    @Query(value = ClienteQueries.DESATIVAR_CLIENTE, nativeQuery = false)
    void desativarCliente(@Param("idCliente") Long idCliente);

    @Query(value = ClienteQueries.REATIVAR_CLIENTE, nativeQuery = false)
    void reativarCliente(@Param("idCliente") Long idCliente);


//    @Query(value = ClienteQueries.RELACIONAR_CLIENTES_ATIVOS_OU_INATIVOS, nativeQuery = false)
//    List<Cliente> findAllAtivos(@Param("dadosDaPesquisa") String dadosDaPesquisa, @Param("ativo") boolean ativo);

    @Query(value = ClienteQueries.BUSCAR_POR_ID, nativeQuery = false)
    Cliente buscarPorId(@Param("idCliente") Long idCliente);
}

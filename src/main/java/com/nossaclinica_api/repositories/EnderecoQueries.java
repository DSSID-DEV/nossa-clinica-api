package com.nossaclinica_api.repositories;

public class EnderecoQueries {
    public static final String BUSCAR_ENDERECO_POR_FILTROS = """
            select distinct endereco from Endereco endereco where 
                endereco.cidade = :cidade and endereco.bairro = :bairro 
                and endereco.tipoLogradouro = :tipoLogradouro 
                and endereco.logradouro = :logradouro
                and endereco.numero = :numero
            """;
}

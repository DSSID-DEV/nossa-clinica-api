package com.nossaclinica.api.repositories.queries;

public class EspecializacaoQueries {

	public static final String BUSCAR_POR_DESCRICAO = 
			"select especializacao from Especializacao especializacao where especializacao.descricao like :descricao";

	
}

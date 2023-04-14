package com.nossaclinica.api.repositories.queries;

public class UsuarioQueries {

	public static final String BUSCAR_POR_USERNAME_AND_NASCIDO_EM = 
			"from Usuario u where u.username = :username and u.nascidoEm = :nascidoEm";

}

package com.nossaclinica.api.repositories.queries;

public class MedicoEspecialistasQueries {

	public static final String LISTAR_POR_ESPECIALIDADE = 
			"select medico from MedicoEspecialista medico join medico.especialidades especialidade where "
			+ "especialidade.idEspecialidade = :idEspecialidade";
}


CREATE TABLE medicos_especialistas (
	medico_id				BIGINT NOT NULL,
	especialista_id			BIGINT NOT NULL,
		
	FOREGN KEY(medico_id) REFERENCES medicos(id_medico),
	FOREGN KEY(especialista_id) REFERENCES especialidades(id_especialidade)
);
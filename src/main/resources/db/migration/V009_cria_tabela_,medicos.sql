CREATE SEQUENCE IF NOT EXISTS medico_id_seq
	INCREMENT 1
	MINVALUE 1
	MAXVALUE 9999999999999
	START 1
	CACHE 1;

CREATE TABLE medicos (
	id_medico				BIGINT DEFAULT NEXTVAL('medico_id_seq'),
	nome					VARCHAR(300)  NOT NULL,
	descricao				VARCHAR(500) NULL,
	usuario_id				BIGINT(30)  NULL,
	
	PRIMARY KEY(id_especialidade)
);